package net.hilaryoi.website.dochakuso;

public class Member {

	String name;
	String links;

	public Member(String name) {

		this.name = name;
		links = String.format("<strong>%s</strong>", name);

	}

	public void addLinks(String links) {
		this.links += links;

	}

}
