package org.graphstream.netlogo.extension.sender;


import org.graphstream.netlogo.extension.GSManager;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.Command;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

/**
 * Implements the {@code step} command
 * 
 * <pre>
 * gs:step senderId step
 * </pre>
 * 
 * @author Stefan Balev
 *
 */
public class Step implements Command {

	
	public String getAgentClassString() {
		return "O";
	}

	@Override
	public Syntax getSyntax() {
		return SyntaxJ.commandSyntax(new int[] { Syntax.StringType(),
				Syntax.NumberType() });
	}

	@Override
	public void perform(Argument[] args, Context context)
			throws ExtensionException, LogoException {
		try {
			String senderId = args[0].getString();
			GSSender sender = GSManager.getSender(senderId);
			double step = args[1].getDoubleValue();
			sender.sendStepBegins(step);
		} catch (LogoException e) {
			throw new ExtensionException(e.getMessage());
		}
	}

}