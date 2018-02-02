# -*- coding=utf-8 -*-
import math
import sys
from texttable import Texttable
from collections import defaultdict
#from Wtemp import *
from operator import itemgetter
from importlib import reload

#读取文件
def readFile(fileData):
    rates=[]
    f=open(fileData,"r")
    data=f.readlines()
    f.close()
    for line in data:
        dataLine=line.split(",")
        rates.append([int(dataLine[0]),int(dataLine[1]),float(dataLine[2])])
    return rates

#创建字典，生成用户评分的数据结构
#   输入：数据集合，格式：用户id\t硬盘id\t用户评分
#   输出:1.用户字典：dic[用户id]=[(Itemid,Item评分)...]
#        2.Item字典：dic[Itemid]=[用户id1,用户id2...]
# userDict[2]=[(1,5),(4,2)].... 表示用户2对Item1的评分是5，对Item4的评分是2
def getUserScoreDataStructure(rates):
    userDict={}
    itemDict={}
    for k in rates:
        user_rank=(k[1],k[2])
        if k[0] in userDict:
            userDict[k[0]].append(user_rank)
        else:
            userDict[k[0]]=[user_rank]
            
        if k[1] in itemDict:
            itemDict[k[1]].append(k[0])
        else:
            itemDict[k[1]]=[k[0]]
    return userDict,itemDict

#建立物品倒排表,计算物品相似度
def itemCF(user_dict):
    N=dict()
    C=defaultdict(defaultdict)
    W=defaultdict(defaultdict)
    for key in user_dict:
        for i in user_dict[key]:
            if i[0] not in N.keys(): #i[0]表示movie_id
                N[i[0]]=0
            N[i[0]]+=1               #N[i[0]]表示评论过某Item的用户数
            for j in user_dict[key]:
                if i==j:
                    continue
                
                if j[0] not in C[i[0]].keys():
                    C[i[0]][j[0]]=0
#                 else:
#                     print('Found:%d,%d' % (i[0],j[0]))
                C[i[0]][j[0]]+=1      #C[i[0]][j[0]]表示Item两两之间的相似度，eg：同时评论过Item1和Item2的用户数
    
#     print(C)
    
    for i,related_item in C.items():
        for j,cij in related_item.items():
            W[i][j]=cij/(N[i]+N[j]-cij)
#             W[i][j]=cij/math.sqrt(N[i]*N[j])
    return W

#Print the matrix for debug
def printMatrix(m):
    rows=[]
    table=Texttable()                                              #创建表格并显示
    table.set_deco(Texttable.HEADER | Texttable.VLINES
        | Texttable.HLINES | Texttable.BORDER)
    table.set_cols_dtype(['t','t','t'])
    table.set_cols_align(["l","l","l"])
    rows.append([u"userId",u"jobId",u"recommondations"])

#结合用户喜好对物品排序
def recommondation(user_id,user_dict,K):
    rank=defaultdict(int)
    l=list()
    W=itemCF(user_dict)
        
    for i,score in user_dict[user_id]: #i为特定用户的Itemid，score为其相应评分
        for j,wj in sorted(W[i].items(),key=itemgetter(1),reverse=True)[0:K]: #sorted()的返回值为list,list的元素为元组
            # j : jobId , m (jobId, score). Filter user_id has already scored jobId [Mod by wx]
            if j in [m[0] for m in user_dict[user_id]]:
                continue
            rank[j]+=score*wj #先找出用户评论过的Item集合，对每一部Itemid，假设其中一部Itemid1,找出与该Item最相似的K部Item，计算出在id1下用户对每部Item的兴趣度，接着迭代整个用户评论过的Item集合，求加权和，再排序，可推荐出前n部Item，我这里取10部。
    l=sorted(rank.items(),key=itemgetter(1),reverse=True)[0:10]
    return l

#获取Item列表
def getItemList(item):
    items={}
    f=open(item,"r",encoding='utf-8')
    movie_content=f.readlines()
    f.close()
    for movie in movie_content:
        movieLine = movie.strip().split(",",maxsplit=1)  
#         movieLine=movie.split("|")
        items[int(movieLine[0])]=movieLine[1:]
    print(items)
    return items

#主程序
if __name__=='__main__':
    reload(sys)
        
    items=getItemList("job.txt") #获取工作列表
    
    rates=readFile("employee_ratings.csv")     #读取文件
    #创建字典
    user_dic,movie_dic=getUserScoreDataStructure(rates)
    user_id=50
    itemRelations=recommondation(user_id,user_dic,80)               #对Item排序
    rows=[]
    table=Texttable()                                              #创建表格并显示
    table.set_deco(Texttable.HEADER | Texttable.VLINES
        | Texttable.HLINES | Texttable.BORDER)
    table.set_cols_dtype(['t','t','t'])
    table.set_cols_align(["l","l","l"])
    rows.append([u"userId",u"jobId",u"recommondations"])
    for i in itemRelations:
        rows.append([user_id,i[0],items[i[0]]])
    
    table.add_rows(rows) 
    print('基于物品推荐')
    print(table.draw())
        
    

        
     
            
    
    
            
                
