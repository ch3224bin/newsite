package com.john_na.common.entity;

public class DaumProfileVo {
//	id 	integer 	사용자 고유 번호
//	nickname 	String 	별명
//	profile_image_url 	String 	55x55 사이즈 프로필 이미지
//	profile_big_image_url
	private String id;
	private String nickname;
	private String profileImageUrl;
	private String profileBigImageUrl;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	public String getProfileBigImageUrl() {
		return profileBigImageUrl;
	}
	public void setProfileBigImageUrl(String profileBigImageUrl) {
		this.profileBigImageUrl = profileBigImageUrl;
	}
}
