package org.iaff.csiaff.thymeleaf.processor;

import java.util.ArrayList;
import java.util.List;

import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Attribute;
import org.thymeleaf.dom.Element;
import org.thymeleaf.dom.Node;
import org.thymeleaf.processor.element.AbstractMarkupSubstitutionElementProcessor;

public class OrderElementTagProcessor extends AbstractMarkupSubstitutionElementProcessor {

	public OrderElementTagProcessor() {
		super("order");
	}

	@Override
	public int getPrecedence() {
		return 1000;
	}

	@Override
	protected List<Node> getMarkupSubstitutes(Arguments arguments, Element element) {

		Attribute page = element.getAttributeFromNormalizedName("page");
		Attribute field = element.getAttributeFromNormalizedName("field");
		Attribute text = element.getAttributeFromNormalizedName("text");

		final Element container = new Element("th:block");
		container.setAttribute("th:replace", String.format("fragments/Ordenacao :: order (%s, %s, '%s')",
				page.getValue(), field.getValue(), text.getValue()));

		final List<Node> nodes = new ArrayList<Node>();
		nodes.add(container);

		return nodes;
	}

}
