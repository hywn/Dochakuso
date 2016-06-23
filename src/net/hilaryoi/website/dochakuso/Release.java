package net.hilaryoi.website.dochakuso;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Release extends Page {

	File dir;

	String releaseTemplate;

	// id example: DCKS-0001
	String id;
	String title;
	String artists;
	String splash;
	String description;
	String download;
	String tracklist;

	// the path to the folder
	public Release(String template, String releaseTemplate, File dir) throws IOException {

		super(template);

		this.releaseTemplate = releaseTemplate;

		this.dir = dir;

		// gets misc info
		getInfo();
		getDescription();
		getTracklist();

	}

	private void getTracklist() throws IOException {

		// template already has <ol> tags

		StringBuilder list = new StringBuilder();

		Scanner scan = new Scanner(new InputStreamReader(new FileInputStream(new File(dir, "tracklist.txt")), "UTF-8"));

		while (scan.hasNextLine()) {

			list.append("<li>");
			list.append(scan.nextLine());
			list.append("</li>");

		}

		scan.close();

		tracklist = list.toString();

	}

	private void getDescription() throws IOException {

		description = FileUtil.getText(new File(dir, "desc.txt")).replaceAll("\n", "<br/>");

	}

	private void getInfo() throws IOException {

		Scanner scan = new Scanner(new InputStreamReader(new FileInputStream(new File(dir, "info.txt")), "UTF-8"));

		while (scan.hasNextLine()) {

			String line = scan.nextLine();

			if (line.startsWith("id=")) {
				id = line.replace("id=", "");

			} else if (line.startsWith("title=")) {
				title = line.replace("title=", "");

			} else if (line.startsWith("artists=")) {
				artists = line.replace("artists=", "");

			} else if (line.startsWith("download=")) {
				download = line.replace("download=", "");

			} else if (line.startsWith("splash=")) {
				splash = line.replace("splash=", "");

			}

		}

		scan.close();

	}

	public String getDownloadLink() {

		if (download == null) {
			return "";

		} else {
			return String.format("<div id=\"download\"><a href=\"%s\">***FREE DOWNLOAD***</a></div>", download);

		}

	}

	@Override
	public String generatePage(String header, String footerTemplate, String... html) {

		return String.format(template, "../", id, header, generateContent(), String.format(footerTemplate, splash));

	}

	public String generateContent() {
		return String.format(releaseTemplate, id, artists, title, id, description, getDownloadLink(), tracklist);

	}

	public String generateLink() {
		return String.format("<a class=\"releaselink\" href=\"./release/%s.html\">%s %s - %s</a> ", id, id, artists,
				title);

	}

}
