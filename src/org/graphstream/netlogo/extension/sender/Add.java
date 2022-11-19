package org.graphstream.netlogo.extension.sender;

import org.graphstream.netlogo.extension.GSManager;
import org.nlogo.api.Agent;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.Command;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Link;
import org.nlogo.api.LogoException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;
import org.nlogo.api.Turtle;

/**
 * Implements the {@code add} command.
 * 
 * <pre>
 * gs:add senderId
 * </per>
 * 
 * @author Stefan Balev
 * 
 */
public class Add implements Command {

	
	public String getAgentClassString() {
		return "TL";
	}

	@Override
	public Syntax getSyntax() {
		return SyntaxJ.commandSyntax(new int[] { Syntax.StringType() });
	}

	@Override
	public void perform(Argument[] args, Context context)
			throws ExtensionException, LogoException {
		try {
			String senderId = args[0].getString();
			GSSender sender = GSManager.getSender(senderId);
			Agent agent = context.getAgent();
			if (agent instanceof Turtle)
				sender.sendNodeAdded(agent.id());
			else if (agent instanceof Link) {
				Link link = (Link) agent;
				sender.sendEdgeAdded(link.end1().id(), link.end2().id(),link.isDirectedLink());
			}
		} catch (LogoException e) {
			throw new ExtensionException(e.getMessage());
		}
	}
}