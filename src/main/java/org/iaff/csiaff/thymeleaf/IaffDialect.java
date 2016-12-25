package org.iaff.csiaff.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.iaff.csiaff.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import org.iaff.csiaff.thymeleaf.processor.MenuAttributeTagProcessor;
import org.iaff.csiaff.thymeleaf.processor.MessageElementTagProcessor;
import org.iaff.csiaff.thymeleaf.processor.OrderElementTagProcessor;
import org.iaff.csiaff.thymeleaf.processor.PaginationElementTagProcessor;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.processor.IProcessor;

// http://www.thymeleaf.org/doc/tutorials/2.1/extendingthymeleaf.html
public class IaffDialect extends AbstractDialect {

	@Override
	public String getPrefix() {
		return "iaff";
	}

    @Override
    public Set<IProcessor> getProcessors() {
        final Set<IProcessor> processors = new HashSet<IProcessor>();
        processors.add(new ClassForErrorAttributeTagProcessor());
        processors.add(new MessageElementTagProcessor());
        processors.add(new OrderElementTagProcessor());
        processors.add(new PaginationElementTagProcessor());
        processors.add(new MenuAttributeTagProcessor());
        return processors;
    }

}