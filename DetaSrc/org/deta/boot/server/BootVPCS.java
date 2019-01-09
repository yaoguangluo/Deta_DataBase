package org.deta.boot.server;
import java.io.IOException;
import org.deta.boot.vpc.controller.ServerInitController;
public class BootVPCS {
	public static void main(String[] args) throws IOException {
		ServerInitController.initServer();
	}
}