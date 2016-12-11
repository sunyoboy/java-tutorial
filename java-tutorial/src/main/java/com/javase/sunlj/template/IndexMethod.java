package com.sunlj.template;

import java.util.List;

import freemarker.template.SimpleNumber;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class IndexMethod implements TemplateMethodModelEx {

	public Object exec(List arguments) throws TemplateModelException {
		if (arguments.size() != 2) {
			throw new TemplateModelException("Wrong arguments");
		}
		
		return new SimpleNumber(((String) arguments.get(1)).indexOf(
				((String) arguments.get(0))) );
	}

/*	public void execute(Environment env,
            Map params,
            TemplateModel[] loopVars,
            TemplateDirectiveBody body) throws TemplateException, IOException {
		
	}*/

}
