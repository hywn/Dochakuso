package net.hilaryoi.website.dochakuso;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class Run {

	static String rootDir = "/mary/dev/workspace1/Dochakuso/files/";
	// static String rootDir = "D:/dev/workspace1/Dochakuso/files/";

	static String pageTemplate;
	static String releaseTemplate;
	static String releaseListTemplate;
	static String header;
	static String footerTemplate;

	public static void main(String[] args) throws IOException {

		System.out.println(Charset.defaultCharset());

		pageTemplate = FileUtil.getText(rootDir + "template/page.html");

		footerTemplate = FileUtil.getText(rootDir + "template/footer.html");

		header = FileUtil.getText(rootDir + "static/header.html");

		genMiscStatic();

		// genReleases();

		genMembers();

		Releases releases = genReleases();

		genTop(releases);

	}

	public static void genMiscStatic() throws IOException {

		About about = new About(pageTemplate);

		FileUtil.write(about.generatePage(header, footerTemplate, FileUtil.getText(rootDir + "static/about.html")),
				rootDir + "about.html");

		Links links = new Links(pageTemplate);

		FileUtil.write(links.generatePage(header, footerTemplate, FileUtil.getText(rootDir + "static/links.html")),
				rootDir + "links.html");

		Offerings offerings = new Offerings(pageTemplate);

		FileUtil.write(
				offerings.generatePage(header, footerTemplate, FileUtil.getText(rootDir + "static/offering.html")),
				rootDir + "bosyuu.html");

	}

	public static void genMembers() throws IOException {

		Members members = new Members(pageTemplate, new File(rootDir + "members.txt"));

		FileUtil.write(members.generatePage(header, footerTemplate), rootDir + "members.html");

	}

	public static void genTop(Releases releases) throws IOException {

		String topTemplate = FileUtil.getText(rootDir + "template/top.html");

		Top top = new Top(pageTemplate, topTemplate, releases);

		FileUtil.write(top.generatePage(header, footerTemplate), rootDir + "index.html");

	}

	public static Releases genReleases() throws IOException {

		String releaseTemplate = FileUtil.getText(rootDir + "template/dlrelease.html");
		String releaseListTemplate = FileUtil.getText(rootDir + "template/releaselist.html");

		Releases releases = new Releases(pageTemplate, releaseListTemplate, releaseTemplate,
				new File(rootDir + "release/data/"));

		FileUtil.write(releases.generatePage(header, footerTemplate, rootDir + "release/"), rootDir + "releases.html");
		FileUtil.write(releases.generateNewPage(header, footerTemplate), rootDir + "newreleases.html");

		return releases;

	}

}
