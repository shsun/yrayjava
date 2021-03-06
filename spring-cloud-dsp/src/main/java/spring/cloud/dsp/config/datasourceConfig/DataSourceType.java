package spring.cloud.dsp.config.datasourceConfig;

public enum DataSourceType {
    MOMENT("dataSource"), COMMENT("commentDataSource"), XAD("xadDataSource");

    private String beanName;

    DataSourceType(String dataSourceType) {
        this.beanName = dataSourceType;
    }

    @Override
    public String toString() {
        return this.beanName;
    }

    public String getType() {
        return this.beanName;
    }

}