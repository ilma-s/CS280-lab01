public class TikTokProfile implements Comparable<TikTokProfile> {
    private String account_id;
    private Double avg_engagement_rate;
    private Boolean is_verified;
    private Integer followers;
    private Integer following;
    private Integer likes;
    private Integer videos_count;

    TikTokProfile(String account_id, double avg_engagement_rate, boolean is_verified, int followers, int following, int likes, int videos_count){
        this.account_id = account_id;
        this.avg_engagement_rate = avg_engagement_rate;
        this.is_verified = is_verified;
        this.followers = followers;
        this.following = following;
        this.likes = likes;
        this.videos_count = videos_count;
    }

    public Integer getFollowers() {
        return this.followers;
    }

    public Boolean getIs_verified() {
        return this.is_verified;
    }

    public Double getLikesPerVideo() {
        return (double) (this.likes / this.videos_count);
    }

    @Override
    public String toString() {
        return "Profile {" + this.account_id + " ," + this.followers + ", " + this.is_verified + this.getLikesPerVideo() + " }";
    }

    @Override
    public int compareTo(TikTokProfile o) {
        return this.followers.compareTo(o.followers);
    }
}
