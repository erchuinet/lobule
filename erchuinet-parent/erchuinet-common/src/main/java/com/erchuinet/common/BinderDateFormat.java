package com.erchuinet.common;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BinderDateFormat extends DateFormat {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private static Log logger = LogFactory.getLog(BinderDateFormat.class);
    private static String[] formats = {"yyyy-MM-dd+HH:mm:ss", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd", "yyyy/MM/dd", "yyyyMMdd" ,"HH:mm:ss","HH:mm"};

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        if (date != null) {
            toAppendTo.append(DateFormatUtils.format(date, formats[0]));
        }
        return toAppendTo;
    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        for (String format : formats) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                Date result = sdf.parse(source);
                pos.setIndex(source.length());
                return result;
            } catch (ParseException ex) {
                //logger.warn("parse date error.", ex);
            }
        }
        return null;
    }
}
