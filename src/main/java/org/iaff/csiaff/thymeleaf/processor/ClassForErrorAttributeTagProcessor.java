package org.iaff.csiaff.thymeleaf.processor;

import java.util.HashMap;
import java.util.Map;

import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.attr.AbstractAttributeModifierAttrProcessor;
import org.thymeleaf.spring4.util.FieldUtils;

//http://www.thymeleaf.org/doc/tutorials/2.1/extendingthymeleaf.html
public class ClassForErrorAttributeTagProcessor extends AbstractAttributeModifierAttrProcessor {

	public ClassForErrorAttributeTagProcessor() {
		super("classforerror");
	}

	public int getPrecedence() {
		return 12000;
	}

	@Override
	protected Map<String, String> getModifiedAttributeValues(Arguments arguments, Element element,
			String attributeName) {
		/*
         * Obtain the attribute value
         */
        final String attributeValue = element.getAttributeValue(attributeName);

		/*
		 * 
		 * /* Apply the corresponding CSS class to the element.
		 */
		final Map<String, String> values = new HashMap<String, String>();

		boolean temErro = FieldUtils.hasErrors(arguments, attributeValue);
				
		if(temErro){
			values.put("class", "has-error");
		}

		return values;
	}

	@Override
	protected ModificationType getModificationType(Arguments arguments, Element element, String attributeName,
			String newAttributeName) {

		// Just in case there already is a value set for the 'class' attribute
		// in the tag, we will append our new value (using a whitespace
		// separator) instead of simply substituting it.
		return ModificationType.APPEND_WITH_SPACE;
	}

	@Override
	protected boolean removeAttributeIfEmpty(Arguments arguments, Element element, String attributeName,
			String newAttributeName) {
		
		// If the resulting 'class' attribute is empty, do not show it at all.
		return true;
	}

	@Override
	protected boolean recomputeProcessorsAfterExecution(Arguments arguments, Element element, String attributeName) {
		
		// There is no need to recompute the element after this processor has executed
		return false;
	}

}
