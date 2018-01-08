package com.liuzhiqiang.config;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/**
 * Created by lzq on 2017/9/9.
 */
public class MultipleDataSource extends AbstractRoutingDataSource {
    private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<String>();

    public static void setDataSourceKey(String dataSource) {
        dataSourceKey.set(dataSource);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceKey.get();
    }
}
