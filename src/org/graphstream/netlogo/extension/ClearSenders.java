package org.graphstream.netlogo.extension;


import java.io.IOException;

import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.Command;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

/**
 * Implements the {@code clear-senders} command
 * 
 * <pre>
 * gs:clear-senders
 * </pre>
 * 
 * @author Stefan Balev
 *
 */
public class ClearSenders implements Command {
	
	public String getAgentClassString() {
		return "O";
	}

	@Override
	public void perform(Argument[] args, Context context)
			throws ExtensionException, LogoException {
		try {
            GSManager.clearSenders();
        } catch (InterruptedException | IOException e) {
            
            e.printStackTrace();
        }
	}

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax();
        
    }
}
