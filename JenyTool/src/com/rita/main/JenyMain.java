package com.rita.main;

import java.util.Scanner;

import com.rita.conts.Commanconstants;
import com.rita.utils.JenUtils;

public class JenyMain {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
        JenUtils jenUtils = new JenUtils(); 
		while (true) {

			System.out.println(Commanconstants.WELCOME_MSG);
			String help = sc.nextLine();

			switch (actualStatement(help)) {

			case Commanconstants.COPY_JOB: {
				System.out.println(Commanconstants.COPY_JOB_WELCOME_MSG);
				System.out.println(Commanconstants.COPY_JOB_SOURCE_PATH_REQUEST);
				String src= sc.nextLine();
				System.out.println(Commanconstants.COPY_JOB_DESTINANTION_PATH_REQUEST);
				String dest= sc.nextLine();
				System.out.println(jenUtils.copy(src, dest));
				break;
			}

			case Commanconstants.CREAT_JOB: {
				System.out.println(Commanconstants.CREAT_JOB_WELCOME_MSG);
				System.out.println(Commanconstants.CREAT_JOB_PATH_REQUEST);
				String path = sc.next();
				System.out.println();
				String fileName = sc.next();
				System.out.println(jenUtils.creat(path, fileName));
				break;
			}

			case Commanconstants.DELETE_JOB: {
				System.out.println(Commanconstants.DELET_JOB_WELCOME_MSG);
				System.out.println(Commanconstants.DELETE_JOB_PATH_REQUEST);
				String path = sc.next();
				System.out.println(jenUtils.delete(path));
				break;
			}

			case Commanconstants.SEARCH_JOB: {
				System.out.println(Commanconstants.SEARCH_JOB_WELCOME_MSG);
				System.out.println(Commanconstants.SEARCH_JOB_FILE_REQUEST);
				String fname = sc.next();
				System.out.println(jenUtils.search(fname));
				break;
			}

			}

		}

	}

	static String actualStatement(String helpText) {

		String actualText = "";

		if (helpText.toUpperCase().contains(Commanconstants.COPY_JOB)) {
			actualText = Commanconstants.COPY_JOB;
		}

		if (helpText.toUpperCase().contains(Commanconstants.CREAT_JOB)) {
			actualText = Commanconstants.CREAT_JOB;
		}

		if (helpText.toUpperCase().contains(Commanconstants.DELETE_JOB)) {
			actualText = Commanconstants.DELETE_JOB;
		}

		if (helpText.toUpperCase().contains(Commanconstants.SEARCH_JOB)) {
			actualText = Commanconstants.SEARCH_JOB;
		}

		return actualText;
	}

}
