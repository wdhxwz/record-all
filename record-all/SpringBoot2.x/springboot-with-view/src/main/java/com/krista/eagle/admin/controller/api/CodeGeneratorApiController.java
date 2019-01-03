package com.krista.eagle.admin.controller.api;

import com.krista.code.generator.configuration.CodeGeneratorProperty;
import com.krista.code.generator.dao.DbOperator;
import com.krista.code.generator.model.DbTypeEnum;
import com.krista.code.generator.model.DropDownVo;
import com.krista.code.generator.model.TableModel;
import com.krista.eagle.admin.model.dto.CodeGeneratorDto;
import com.krista.eagle.admin.model.dto.CodeGeneratorStepOneDto;
import com.krista.extend.base.response.JsonResponse;
import com.krista.extend.utils.JsonUtil;
import com.krista.extend.utils.ValidationUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * CodeGeneratorApiController
 *
 * @author krista
 * @version V1.0
 * @since 2018/12/23 11:37
 */
@RestController
@RequestMapping("/codegenerator")
public class CodeGeneratorApiController {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CodeGeneratorApiController.class);

    /**
     * 这个要放到session中
     */
    @Autowired
    CodeGeneratorProperty codeGeneratorProperty;

    private DbOperator dbOperator;

    @GetMapping(value = "listDbTypes")
    public JsonResponse<List<DropDownVo>> listDbTypes() {
        List<DropDownVo> list = new ArrayList<>();
        Stream.of(DbTypeEnum.values()).forEach(dbTypeEnum -> {
            DropDownVo dropDownVo = new DropDownVo();
            dropDownVo.setValue(dbTypeEnum.getCode());
            dropDownVo.setText(dbTypeEnum.getDesc());
            dropDownVo.setSelected(CodeGeneratorProperty.getDefaultDb().equals(dbTypeEnum.getCode()));

            list.add(dropDownVo);
        });

        return new JsonResponse<>(list);
    }

    @PostMapping(value = "step1")
    public JsonResponse<List<DropDownVo>> step1(@RequestBody CodeGeneratorStepOneDto param) {
        // 成功后，返回数据库列表
        LOGGER.info("code generator step 1 :{}", JsonUtil.toJson(param));
        String errorMsg = ValidationUtil.validate(param);
        if (StringUtils.isNotEmpty(errorMsg)) {
            LOGGER.warn("数据验证异常{}", errorMsg);
            return new JsonResponse<>(500, "数据验证错误：" + errorMsg);
        }

        codeGeneratorProperty.setDbType(param.getDbType());
        codeGeneratorProperty.setDbPort(param.getPort());
        codeGeneratorProperty.setDbServer(param.getHost());
        codeGeneratorProperty.setPassword(param.getPassword());
        codeGeneratorProperty.setUserName(param.getUserName());

        String url = codeGeneratorProperty.getDbUrl();
        LOGGER.info("dbUrl:{}", url);
        try {
            if (dbOperator != null) {
                dbOperator.close();
            }
            dbOperator = new DbOperator(url, param.getUserName(), param.getPassword());
            return new JsonResponse<>(
                    dbOperator.databases().stream()
                            .map(db -> new DropDownVo(db, db))
                            .collect(Collectors.toList())
            );
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            return new JsonResponse<>(500, "数据库连接异常,请检查填写数据库信息");
        }
    }

    @GetMapping(value = "getTableList")
    public JsonResponse<List<TableModel>> getTableList(String dbName) {
        List<TableModel> tableModels = dbOperator.listTables(dbName);
        tableModels.forEach(
                tableModel -> codeGeneratorProperty.addTableComment(
                        tableModel.getTableName(), tableModel.getComment())
        );

        return new JsonResponse<>(tableModels);
    }

    @PostMapping(value = "generate")
    public JsonResponse<Boolean> generate(@RequestBody CodeGeneratorDto dto) {
        LOGGER.info(JsonUtil.toJson(dto));

        return new JsonResponse<>(true);
    }
}
