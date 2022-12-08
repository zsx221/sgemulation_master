package com.macrochina.net.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import javax.xml.datatype.XMLGregorianCalendar;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.*;

public class BeanUtils extends org.springframework.beans.BeanUtils {

    private static Log logger = LogFactory.getLog(BeanUtils.class);

    public static void copyPropertiesIsNotNull(Object source, Object target, @Nullable Class<?> editable, @Nullable String... ignoreProperties) throws BeansException {

        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        Class<?> actualEditable = target.getClass();

        if (editable != null) {
            if (!editable.isInstance(target)) {
                throw new IllegalArgumentException("Target class [" + target.getClass().getName() + "] not assignable to Editable class [" + editable.getName() + "]");
            }

            actualEditable = editable;
        }

        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        List<String> ignoreList = ignoreProperties != null ? Arrays.asList(ignoreProperties) : null;
        PropertyDescriptor[] var7 = targetPds;
        int var8 = targetPds.length;

        for (int var9 = 0; var9 < var8; ++var9) {
            PropertyDescriptor targetPd = var7[var9];
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }

                            Object value = readMethod.invoke(source);
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            //添加扩展，当source bean字段为空或者空字符串时取消复制
                            if (!StringUtils.isEmpty(value)) {
                                writeMethod.invoke(target, value);
                            }
                        } catch (Throwable var15) {
                            throw new FatalBeanException("Could not copy property '" + targetPd.getName() + "' from source to target", var15);
                        }
                    }
                }
            }
        }

    }

    public static Object getObjFieldValue(Object obj, String fieldName) {
        Object rtn = null;
        if (obj == null || StringUtils.isEmpty(fieldName)) {
            return rtn;
        }
        try {
            logger.debug("fieldName=" + fieldName);
            //Field field = obj.getClass().getDeclaredField(fieldName);
            Method method = null;
            try {
                method = obj.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length()));
            } catch (Exception e) {
                method = obj.getClass().getMethod("get" + fieldName.substring(0, 2).toUpperCase() + fieldName.substring(2, fieldName.length()));
            }
            Object objField = method.invoke(obj);
            rtn = objField;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return rtn;
    }

    public static boolean setObjFieldValue(Object obj, String fieldName, Object fieldValue) {
        boolean flag = false;
        if (obj == null || StringUtils.isEmpty(fieldName)) {
            return flag;
        }
        try {
            logger.debug("fieldName=" + fieldName + ",fieldValue:" + fieldValue);
            //Field field = obj.getClass().getDeclaredField(fieldName);
            Method method = obj.getClass().getMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length()), fieldValue.getClass());
            method.invoke(obj, fieldValue);
            flag = true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return flag;
    }

    public static void newObjField(Map tMap, String fieldName) {
        Object obj = tMap.get("tField");
        if (obj == null || StringUtils.isEmpty(fieldName)) {
            return;
        }
        try {
            logger.debug("在对象中查找字段"+" fieldName=" + fieldName + "  obj=" + obj);
            if (List.class.isAssignableFrom(obj.getClass())) {
                obj = ((List<?>) obj).get(0);
            }
            Field field = obj.getClass().getDeclaredField(fieldName);
            if (field != null) {
                field.setAccessible(true);
                Object fieldValue = field.get(obj);
                if (fieldValue == null) {
                    logger.debug("获取字段的数据类型:" + field.getType().getName()+",fieldName="+fieldName);
                    Class fieldClass = Class.forName(field.getType().getName());
                    Object fobj = null;
                    if (List.class.isAssignableFrom(fieldClass)) {
                        //list 字段类型处理
                        Type genericType = field.getGenericType();
                        if (genericType != null) {
                            logger.debug("获取到List字段的泛型数据类型：" + genericType.getTypeName()+",,fieldName="+fieldName);
                            if (genericType instanceof ParameterizedType) {
                                ParameterizedType pt = (ParameterizedType) genericType;
                                // 得到泛型里的class类型对象
                                Class<?> actualTypeArgument = (Class<?>) pt.getActualTypeArguments()[0];
                                Object actualType = actualTypeArgument.newInstance();
                                List subObj = new ArrayList();
                                if(String.class.isAssignableFrom(actualType.getClass())){
                                    //String 类型不添加
                                }else{
                                    subObj.add(actualType);
                                }
                                fobj = subObj;
                            }
                        }
                    } else if (Enum.class.isAssignableFrom(fieldClass)) {
                        //枚举类型字段处理不在这里初始化
                    } else if (Boolean.class.isAssignableFrom(fieldClass) || String.class.isAssignableFrom(fieldClass)) {
                        //boolean,String值的字段不初始化
                    } else if (XMLGregorianCalendar.class.isAssignableFrom(fieldClass)) {
                        //不初始化
                    }else if(BigDecimal.class.isAssignableFrom(fieldClass)){
                        //不初始化
                    } else {
                        fobj = fieldClass.newInstance();
                    }
                    tMap.put("tObj", obj);
                    tMap.put("tFieldName", fieldName);
                    tMap.put("tField", fobj);
                    field.set(obj, fobj);
                } else {
                    tMap.put("tObj", obj);
                    tMap.put("tFieldName", fieldName);
                    tMap.put("tField", fieldValue);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public static boolean setObjFieldValueByField(Object obj, String field, Object fieldValue) {
        boolean flag = false;
        if (obj == null || field == null || StringUtils.isEmpty(fieldValue)) {
            return flag;
        }
        try {
            logger.debug("obj:" + obj + " will set fieldName:" + field + " to fieldValue:" + fieldValue);
            Field f = obj.getClass().getDeclaredField(field);
            f.setAccessible(true);
            Class fieldClass = Class.forName(f.getType().getName());
            if (List.class.isAssignableFrom(fieldClass)) {
                //list 字段类型处理
                Type genericType = f.getGenericType();
                if (genericType != null) {
                    logger.debug("List字段的泛型数据类型：" + genericType.getTypeName()+",,fieldName="+field);
                    if (genericType instanceof ParameterizedType) {
                        ParameterizedType pt = (ParameterizedType) genericType;
                        // 得到泛型里的class类型对象
                        Class<?> actualTypeArgument = (Class<?>) pt.getActualTypeArguments()[0];
                        if(String.class.getName().equals(actualTypeArgument.getTypeName())){
                            List subObj=(List)f.get(obj);
                            if(subObj==null){
                                subObj = new ArrayList();
                            }
                            subObj.add(fieldValue);
                            f.set(obj, subObj);
                        }
                    }
                }
            } else if (f.getType().isEnum()) {
                f.set(obj, Enum.valueOf((Class<Enum>) f.getType(), fieldValue.toString()));
            } else {
                f.set(obj, fieldValue);
            }
            flag = true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }
}
