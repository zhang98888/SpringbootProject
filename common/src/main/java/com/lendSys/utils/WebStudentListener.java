package com.lendSys.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import java.util.ArrayList;
import java.util.List;


public class WebStudentListener <T> extends AnalysisEventListener<T> {
    public List<T> list = new ArrayList<>();
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        System.out.println(t.toString()+"t");
        list.add(t);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    public List<T> getExcelData(){

        return list;
    }
}
