package org.iaff.csiaff.thymeleaf.processor;

import java.util.ArrayList;
import java.util.List;

import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.dom.Node;
import org.thymeleaf.processor.element.AbstractMarkupSubstitutionElementProcessor;

// http://www.thymeleaf.org/doc/tutorials/2.1/extendingthymeleaf.html
public class MessageElementTagProcessor extends AbstractMarkupSubstitutionElementProcessor{

	public MessageElementTagProcessor() {
		super("message");
	}

	@Override
	public int getPrecedence() {
		return 1000;
	}

	@Override
	protected List<Node> getMarkupSubstitutes(Arguments arguments, Element element) {

        final Element container = new Element("th:block");
        container.setAttribute("th:replace", "fragments/MensagensErroValidacao");
        
        final Element container2 = new Element("th:block");
        container2.setAttribute("th:replace", "fragments/MensagemSucesso");
        
        final List<Node> nodes = new ArrayList<Node>();
        nodes.add(container); 
        nodes.add(container2);
        
        return nodes;
	}

}
