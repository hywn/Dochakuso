package net.hilaryoi.website.dochakuso;

public class Links extends Page {

	public Links(String template) {
		super(template);

	}

	@Override
	public String generatePage(String header, String footerTemplate, String... html) {

		// only html should be about contents
		return String.format(template, "./", "- LINKS -", header, html[0],
				String.format(footerTemplate, "まだ君ガラケーなの？ｸﾞﾌﾌｗ"));

	}

}
