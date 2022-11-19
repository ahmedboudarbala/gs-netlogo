package org.graphstream.netlogo.extension.receiver;


import org.graphstream.netlogo.extension.GSManager;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.Command;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

/**
 * Implements the {@code flush} command.
 * 
 * <pre>
 * gs:flush receiverId
 * </pre>
 * 
 * @author Stefan Balev
 * 
 */
public class Flush implements Command {
	
	public String getAgentClassString() {
		return "O";
	}

	@Override
	public Syntax getSyntax() {
		return SyntaxJ.commandSyntax(new int[] { Syntax.StringType() });
	}

	@Override
	public void perform(Argument[] args, Context context)
			throws ExtensionException, LogoException {
		try {
			String receiverId = args[0].getString();
			GSReceiver receiver = GSManager.getReceiver(receiverId);
			receiver.flush();
		} catch (LogoException e) {
			throw new ExtensionException(e.getMessage());
		}

	}

}