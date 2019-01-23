package ${basePackage}.service;

import ${basePackage}.model.${modelNameUpperCamel};


/**
 * ${modelNameUpperCamel}服务
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${modelNameUpperCamel}Service {
	void add(${modelNameUpperCamel} obj);

    ${modelNameUpperCamel} get(Long id);

	void delete(Long id);

	void update(${modelNameUpperCamel} obj);
}
