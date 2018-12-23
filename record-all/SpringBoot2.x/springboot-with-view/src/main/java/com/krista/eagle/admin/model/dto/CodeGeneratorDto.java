package com.krista.eagle.admin.model.dto;

import java.util.List;

/**
 * CodeGeneratorDto
 *
 * @author krista
 * @version V1.0
 * @since 2018/12/23 21:45
 */
public class CodeGeneratorDto {
    private String basePackage;
    private String author;
    private boolean isGenerateModel;
    private boolean isGenerateMapper;
    private boolean isGenerateMapperXml;
    private boolean isGenerateService;
    private List<String> tables;

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isGenerateModel() {
        return isGenerateModel;
    }

    public void setGenerateModel(boolean generateModel) {
        isGenerateModel = generateModel;
    }

    public boolean isGenerateMapper() {
        return isGenerateMapper;
    }

    public void setGenerateMapper(boolean generateMapper) {
        isGenerateMapper = generateMapper;
    }

    public boolean isGenerateMapperXml() {
        return isGenerateMapperXml;
    }

    public void setGenerateMapperXml(boolean generateMapperXml) {
        isGenerateMapperXml = generateMapperXml;
    }

    public boolean isGenerateService() {
        return isGenerateService;
    }

    public void setGenerateService(boolean generateService) {
        isGenerateService = generateService;
    }

    public List<String> getTables() {
        return tables;
    }

    public void setTables(List<String> tables) {
        this.tables = tables;
    }
}
