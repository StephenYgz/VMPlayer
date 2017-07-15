package com.itheima.vmplayer.model;

import java.util.List;

/**
 * Created by wschun on 2016/12/21.
 */

public class MvBean {

    private List<VideosBean> videos;

    public List<VideosBean> getVideos() {
        return videos;
    }

    public void setVideos(List<VideosBean> videos) {
        this.videos = videos;
    }

    public static class VideosBean {
        /**
         * albumImg : http://img3.yytcdn.com/video/mv/161220/2754429/-M-b219cdd83c070804991d12c08c223fca_640x360.jpg
         * artistName : 吴亦凡 & 谭晶
         * artists : [{"artistId":34611,"artistName":"吴亦凡"},{"artistId":1602,"artistName":"谭晶"}]
         * description : “唐僧”吴亦凡与“阿凡达妹妹”谭晶首度合作演唱!诙谐有趣的歌词,不同的唱法,不同的曲风混搭,莫名的好听!
         * duration : 213
         * hdUrl : http://hc.yinyuetai.com/uploads/videos/common/8A6001591BC0658275BC5C71BB079042.mp4?sc=4d66a52f7f2aba10&br=776&rd=Android
         * hdVideoSize : 20742224
         * id : 2754429
         * linkId : 0
         * playListPic : http://img3.yytcdn.com/video/mv/161220/2754429/-M-b219cdd83c070804991d12c08c223fca_240x135.jpg
         * posterPic : http://img3.yytcdn.com/video/mv/161220/2754429/-M-b219cdd83c070804991d12c08c223fca_240x135.jpg
         * regdate : 2016-12-20 18:27
         * shdUrl : http://he.yinyuetai.com/uploads/videos/common/87B101591BC58FF52C85A1DC9BEDC704.mp4?sc=c6c07c25383ee3a8&br=3122&rd=Android
         * shdVideoSize : 83396220
         * status : 200
         * thumbnailPic : http://img3.yytcdn.com/video/mv/161220/2754429/-M-b219cdd83c070804991d12c08c223fca_240x135.jpg
         * title : 乖乖 电影《西游伏妖篇》宣传主题曲
         * totalComments : 50
         * totalMobileViews : 18092
         * totalPcViews : 16813
         * totalViews : 34905
         * uhdUrl : http://hd.yinyuetai.com/uploads/videos/common/D2B501591BC58FFE56FD5CDCF86282E5.mp4?sc=ef705aacc3f58a97&br=1098&rd=Android
         * uhdVideoSize : 29343031
         * url : http://hc.yinyuetai.com/uploads/videos/common/8A6001591BC0658275BC5C71BB079042.mp4?sc=4d66a52f7f2aba10&br=776&rd=Android
         * videoSize : 20742224
         * videoSourceTypeName : music_video
         */

        private String albumImg;
        private String artistName;
        private String description;
        private int duration;
        private String hdUrl;
        private int hdVideoSize;
        private int id;
        private int linkId;
        private String playListPic;
        private String posterPic;
        private String regdate;
        private String shdUrl;
        private int shdVideoSize;
        private int status;
        private String thumbnailPic;
        private String title;
        private int totalComments;
        private int totalMobileViews;
        private int totalPcViews;
        private int totalViews;
        private String uhdUrl;
        private int uhdVideoSize;
        private String url;
        private int videoSize;
        private String videoSourceTypeName;
        private List<ArtistsBean> artists;

        public String getAlbumImg() {
            return albumImg;
        }

        public void setAlbumImg(String albumImg) {
            this.albumImg = albumImg;
        }

        public String getArtistName() {
            return artistName;
        }

        public void setArtistName(String artistName) {
            this.artistName = artistName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getHdUrl() {
            return hdUrl;
        }

        public void setHdUrl(String hdUrl) {
            this.hdUrl = hdUrl;
        }

        public int getHdVideoSize() {
            return hdVideoSize;
        }

        public void setHdVideoSize(int hdVideoSize) {
            this.hdVideoSize = hdVideoSize;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLinkId() {
            return linkId;
        }

        public void setLinkId(int linkId) {
            this.linkId = linkId;
        }

        public String getPlayListPic() {
            return playListPic;
        }

        public void setPlayListPic(String playListPic) {
            this.playListPic = playListPic;
        }

        public String getPosterPic() {
            return posterPic;
        }

        public void setPosterPic(String posterPic) {
            this.posterPic = posterPic;
        }

        public String getRegdate() {
            return regdate;
        }

        public void setRegdate(String regdate) {
            this.regdate = regdate;
        }

        public String getShdUrl() {
            return shdUrl;
        }

        public void setShdUrl(String shdUrl) {
            this.shdUrl = shdUrl;
        }

        public int getShdVideoSize() {
            return shdVideoSize;
        }

        public void setShdVideoSize(int shdVideoSize) {
            this.shdVideoSize = shdVideoSize;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getThumbnailPic() {
            return thumbnailPic;
        }

        public void setThumbnailPic(String thumbnailPic) {
            this.thumbnailPic = thumbnailPic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getTotalComments() {
            return totalComments;
        }

        public void setTotalComments(int totalComments) {
            this.totalComments = totalComments;
        }

        public int getTotalMobileViews() {
            return totalMobileViews;
        }

        public void setTotalMobileViews(int totalMobileViews) {
            this.totalMobileViews = totalMobileViews;
        }

        public int getTotalPcViews() {
            return totalPcViews;
        }

        public void setTotalPcViews(int totalPcViews) {
            this.totalPcViews = totalPcViews;
        }

        public int getTotalViews() {
            return totalViews;
        }

        public void setTotalViews(int totalViews) {
            this.totalViews = totalViews;
        }

        public String getUhdUrl() {
            return uhdUrl;
        }

        public void setUhdUrl(String uhdUrl) {
            this.uhdUrl = uhdUrl;
        }

        public int getUhdVideoSize() {
            return uhdVideoSize;
        }

        public void setUhdVideoSize(int uhdVideoSize) {
            this.uhdVideoSize = uhdVideoSize;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getVideoSize() {
            return videoSize;
        }

        public void setVideoSize(int videoSize) {
            this.videoSize = videoSize;
        }

        public String getVideoSourceTypeName() {
            return videoSourceTypeName;
        }

        public void setVideoSourceTypeName(String videoSourceTypeName) {
            this.videoSourceTypeName = videoSourceTypeName;
        }

        public List<ArtistsBean> getArtists() {
            return artists;
        }

        public void setArtists(List<ArtistsBean> artists) {
            this.artists = artists;
        }

        public static class ArtistsBean {
            /**
             * artistId : 34611
             * artistName : 吴亦凡
             */

            private int artistId;
            private String artistName;

            public int getArtistId() {
                return artistId;
            }

            public void setArtistId(int artistId) {
                this.artistId = artistId;
            }

            public String getArtistName() {
                return artistName;
            }

            public void setArtistName(String artistName) {
                this.artistName = artistName;
            }
        }
    }
}
