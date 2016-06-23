package net.hilaryoi.website.dochakuso;

public class Offerings extends Page {

	public Offerings(String template) {
		super(template);

	}

	@Override
	public String generatePage(String header, String footerTemplate, String... html) {

		// only html should be about contents
		return String.format(template, "./", "ドチャクソ募集ページ", header, html[0],
				String.format(footerTemplate, "おめでとうとドチャクソって似てる"));

	}

}
