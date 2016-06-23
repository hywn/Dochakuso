package net.hilaryoi.website.dochakuso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Members extends Page {

	ArrayList<Member> members;

	public Members(String pageTemplate, File membersFile) throws IOException {

		super(pageTemplate);

		String[] lines = FileUtil.getText(membersFile).split("\n");

		members = new ArrayList<Member>();

		Member currMember = null;

		for (String line : lines) {

			if (line.startsWith("//")) {

				if (currMember != null) {

					members.add(currMember);

				}

				currMember = new Member(line.replaceFirst("//", ""));

			} else if (line.contains("=")) {

				String key = line.substring(0, line.indexOf("="));
				String value = line.substring(line.indexOf("=") + 1, line.length());

				currMember.addLinks(String.format("<br/><a class=\"memberlink\" href=\"%s\">%s</a>", value, key));

			}

		}

		members.add(currMember);

	}

	public String generateMembersList() {

		StringBuilder list = new StringBuilder("<table id=\"members\">");

		for (Member member : members) {

			list.append("<tr><td>");
			list.append(String.format("<img class=\"memberimg\" src=\"%s\"></img>",
					"./img/member/" + member.name + ".png"));
			list.append("</td><td>");
			list.append(member.links);
			list.append("</td></tr>");

		}

		list.append("</table>");

		return list.toString();

	}

	@Override
	public String generatePage(String header, String footerTemplate, String... html) throws IOException {

		return String.format(template, "./", "容疑者", header, generateMembersList(),
				String.format(footerTemplate, "こんなことしてないで田植えしろ"));

	}

}
