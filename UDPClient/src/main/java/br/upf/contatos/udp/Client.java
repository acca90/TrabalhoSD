package br.upf.contatos.udp;

import br.upf.contatos.udp.cli.Command;
import com.beust.jcommander.JCommander;

/**
 *
 * @author Mauricley
 */
public class Client {
    private static Command cmd;
    private static JCommander parser;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        cmd = new Command();
        parser = new JCommander(cmd);
        try {
            parser.parse(args);
            if (cmd.valida()) {
                try {
                    new CommandProcessor(cmd).processa();
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            parser.usage();
            System.exit(0);
        }
    }
}
