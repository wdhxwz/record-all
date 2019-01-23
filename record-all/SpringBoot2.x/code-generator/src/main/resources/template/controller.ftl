package ${basePackage}.web.controller.api;

import com.duowan.common.web.view.JsonView;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import ${basePackage}.util.helper.PageHelper;
import ${basePackage}.util.page.PageQuery;
import ${basePackage}.util.page.PageResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* Created by tangxingjian on 2018-11-05 18:58:09.
*/
/**
 * Created by ${author} on ${date}.
 */
@Controller
@RequestMapping("/admin${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {

    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @PostMapping("/add.do")
    public JsonView add(${modelNameUpperCamel} obj) {
        ${modelNameLowerCamel}Service.add(obj);
        return new JsonView(true);
    }


    @PostMapping("/update.do")
    public JsonView update(${modelNameUpperCamel} obj) {
        ${modelNameLowerCamel}Service.update(obj);
        return new JsonView(true);
    }

    @PostMapping("/get.do")
    public JsonView get(@RequestParam Long id) {
        ${modelNameUpperCamel} obj = ${modelNameLowerCamel}Service.get(id);
        return new JsonView(obj);
    }

    @PostMapping("/list.do")
    public JsonView list(HttpServletRequest request, ModelMap modelMap) {
        PageQuery pageQuery = PageHelper.getPageQuery(request);
        PageResult result = ${modelNameLowerCamel}Service.list(pageQuery);
        @SuppressWarnings("unchecked")
        List<${modelNameUpperCamel}> list = result.curRecordList();
        modelMap.put("list", list);
        modelMap.put("allRecordCount", result.getAllRecordCount());
        return new JsonView(modelMap);
    }
}
