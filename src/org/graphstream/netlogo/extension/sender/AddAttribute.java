package org.graphstream.netlogo.extension.sender;




import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.graphstream.netlogo.extension.GSManager;
import org.graphstream.ui.graphicGraph.GraphPosLengthUtils;
import org.nlogo.api.Agent;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.Command;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Link;
import org.nlogo.api.LogoException;
import org.nlogo.core.LogoList;
import org.nlogo.api.Observer;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;
import org.nlogo.api.Turtle;

/**
 * Implements the {@code add-attribute} command.
 * 
 * <pre>
 * gs:add-attribute senderId attribute value
 * 
 * @author Stefan Balev
 * 
 */
public class AddAttribute implements Command {
	
	public String getAgentClassString() {
		return "OTL";
	}

	@Override
	public Syntax getSyntax() {
		return SyntaxJ.commandSyntax(new int[] {
				Syntax.StringType(),
				Syntax.StringType(),
				Syntax.NumberType() | Syntax.BooleanType()
						| Syntax.StringType() | Syntax.ListType() });
	}

	/* (non-Javadoc)
	 * @see org.nlogo.api.Command#perform(org.nlogo.api.Argument[], org.nlogo.api.Context)
	 */
	@Override
	public void perform(Argument[] args, Context context)
			throws ExtensionException, LogoException {
		try {
			String senderId = args[0].getString();
			GSSender sender = GSManager.getSender(senderId);
			String attribute = args[1].getString();
			Object value = argToNetStream(args[2]);
			Agent agent = context.getAgent();
			if (agent instanceof Observer)
				sender.sendGraphAttributeAdded(attribute, value);
			else if (agent instanceof Turtle)
				sender.sendNodeAttributeAdded(agent.id(), attribute, value);
			else if (agent instanceof Link) {
				Link link = (Link) agent;
				sender.sendEdgeAttributeAdded(link.end1().id(), link.end2().id(), attribute, value);
			}
		} catch (LogoException e) {
			throw new ExtensionException(e.getMessage());
		}
	}

	protected static Object argToNetStream(Argument arg) throws ExtensionException, LogoException {

		Object value = arg.get();
		
		if (value instanceof Boolean || value instanceof Double	|| value instanceof String)
			return value;
		
		LogoList list = arg.getList();
		if (list.isEmpty())
			throw new ExtensionException("The list must not be empty");
		
		Class<?> elementClass = list.get(0).getClass();
		

        for (int i = 0; i < list.length(); i++)
		 {
            Object o = list.get(i);
			if (!(o instanceof Boolean) && !(o instanceof Double))
				throw new ExtensionException(
						"The list elements must be of type boolean or number");
			if (!o.getClass().equals(elementClass))
				throw new ExtensionException(
						"The list elements must be all of the same type");
		}
		ArrayList<Double> ArrLis = new ArrayList<Double>();
		ArrLis.add((Double) list.get(0));
		ArrLis.add((Double) list.get(1));
		
		
		return ArrLis.toArray();
	}
}