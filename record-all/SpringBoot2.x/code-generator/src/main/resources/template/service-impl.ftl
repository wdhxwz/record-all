package ${basePackage}.service.impl;

import ${basePackage}.dao.${modelNameUpperCamel}Mapper;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * ${modelNameUpperCamel}服务实现
 *
 * @author ${author}
 * @since ${date}
 */
@Service
public class ${modelNameUpperCamel}ServiceImpl implements ${modelNameUpperCamel}Service {
    @Autowired
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;


    @Override
	public void add(${modelNameUpperCamel} obj){
        ${modelNameLowerCamel}Mapper.insertSelective(obj);
    }

    @Override
	public ${modelNameUpperCamel} get(Long id){
        return ${modelNameLowerCamel}Mapper.selectByPrimaryKey(id);
    }

    @Override
	public void delete(Long id){
        ${modelNameLowerCamel}Mapper.deleteByPrimaryKey(id);
    }

    @Override
	public void update(${modelNameUpperCamel} obj){
        ${modelNameLowerCamel}Mapper.updateByPrimaryKeySelective(obj);
    }
}
