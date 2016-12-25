package org.iaff.csiaff.thymeleaf.processor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.thymeleaf.Arguments;
import org.thymeleaf.Configuration;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.attr.AbstractAttributeModifierAttrProcessor;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;

public class MenuAttributeTagProcessor extends AbstractAttributeModifierAttrProcessor {

	public MenuAttributeTagProcessor() {
		super("menu");
	}

	public int getPrecedence() {
		return 1000;
	}

	@Override
	protected Map<String, String> getModifiedAttributeValues(Arguments arguments, Element element,
			String attributeName) {

		final Configuration configuration = arguments.getConfiguration();

		/*
		 * Obtain the attribute value
		 */
		final String attributeValue = element.getAttributeValue(attributeName);

		/*
		 * Obtain the Thymeleaf Standard Expression parser
		 */
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);

		/*
		 * Parse the attribute value as a Thymeleaf Standard Expression
		 */
		final IStandardExpression expression = parser.parseExpression(configuration, arguments, attributeValue);

		/*
		 * Execute the expression just parsed
		 */
		final String menu = (String) expression.execute(configuration, arguments);

		/*
		 * Apply the corresponding CSS class to the element.
		 */
		final Map<String, String> values = new HashMap<String, String>();

		HttpServletRequest request = ((IWebContext) arguments.getContext()).getHttpServletRequest();
		String uri = request.getRequestURI();

		if (uri.startsWith(menu)) {
			// torna ativo o menu da página selecionada
			values.put("class", "is-active");
		}

		return values;

	}

	@Override
	protected ModificationType getModificationType(Arguments arguments, Element element, String attributeName,
			String newAttributeName) {
		// TODO Auto-generated method stub
		return ModificationType.APPEND_WITH_SPACE;
	}

	@Override
	protected boolean removeAttributeIfEmpty(Arguments arguments, Element element, String attributeName,
			String newAttributeName) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected boolean recomputeProcessorsAfterExecution(Arguments arguments, Element element, String attributeName) {
		// TODO Auto-generated method stub
		return false;
	}

}