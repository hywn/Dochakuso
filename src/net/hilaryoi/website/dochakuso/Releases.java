package net.hilaryoi.website.dochakuso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Releases extends Page {

	String releaseListTemplate;

	String newestReleaseId;

	Release newestRelease;

	ArrayList<Release> releases;

	ArrayList<Release> dlRelease, cdRelease;

	public Releases(String pageTemplate, String releaseListTemplate, String releaseTemplate, File dir)
			throws IOException {

		super(pageTemplate);

		this.releaseListTemplate = releaseListTemplate;

		releases = new ArrayList<Release>();

		File[] files = dir.listFiles();

		newestReleaseId = FileUtil.getText(new File(dir, "newest.txt")).trim();

		System.out.println("Looking for new release: " + newestReleaseId);

		for (File file : files) {

			// all files are directories but check anyway
			if (file.isDirectory()) {

				Release release = new Release(pageTemplate, releaseTemplate, file);

				if (release.id.equals(newestReleaseId)) {
					newestRelease = release;
					System.out.println("Newest release found: " + release.id);

				}

				releases.add(release);

			}

		}

	}

	@Override
	public String generatePage(String header, String footerTemplate, String... html) throws IOException {

		ArrayList<Release> dlRelease = new ArrayList<Release>();
		ArrayList<Release> cdRelease = new ArrayList<Release>();

		for (Release release : releases) {

			/*
			 * Using html[] as more args
			 */

			FileUtil.write(release.generatePage(header, footerTemplate), html[0] + release.id + ".html");

			// separate cd release from dl

			if (release.id.startsWith("DCKSC")) {

				cdRelease.add(release);

			} else {

				dlRelease.add(release);

			}

			dlRelease.sort(new DlComparator());
			cdRelease.sort(new CdComparator());

			this.dlRelease = dlRelease;
			this.cdRelease = cdRelease;

			Collections.reverse(dlRelease);
			Collections.reverse(cdRelease);

		}

		return String.format(template, "./", "- RELEASES -", header,
				String.format(releaseListTemplate, getList(dlRelease), getList(cdRelease)),
				String.format(footerTemplate, "財布あるところ散財の神舞い降りにけり"));

	}

	public String generateNewPage(String header, String footerTemplate) {

		Collections.reverse(dlRelease);
		Collections.reverse(cdRelease);

		return String.format(template, "./", "- RELEASES -", header, getImageList(dlRelease),
				String.format(footerTemplate, "財布あるところ散財の神舞い降りにけり"));

	}

	public String getImageList(List<Release> list) {

		StringBuilder b = new StringBuilder();

		for (Release r : list) {

			b.append(String.format("<img class=\"listjacket\" href=\"%s\" src=\"%s\"></img>", "./release" + r.id,
					"./release/data/" + r.id + "/jacket.png"));

		}

		return b.toString();

	}

	public String getList(List<Release> list) {

		StringBuilder builder = new StringBuilder();

		for (Release r : list) {

			builder.append("<li>");
			builder.append(r.generateLink());
			builder.append("</li>");

		}

		return builder.toString();

	}

	public Release getNewestRelease() {
		return newestRelease;

	}

	private class DlComparator implements Comparator<Release> {

		@Override
		public int compare(Release o1, Release o2) {

			int r1 = Integer.valueOf(o1.id.replaceAll("DCKS-", ""));
			int r2 = Integer.valueOf(o2.id.replaceAll("DCKS-", ""));

			return r2 - r1;

		}

	}

	private class CdComparator implements Comparator<Release> {

		@Override
		public int compare(Release o1, Release o2) {

			int r1 = Integer.valueOf(o1.id.replaceAll("DCKSC-", ""));
			int r2 = Integer.valueOf(o2.id.replaceAll("DCKSC-", ""));

			return r2 - r1;

		}

	}

}
