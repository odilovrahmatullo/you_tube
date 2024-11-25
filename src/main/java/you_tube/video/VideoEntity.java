package you_tube.video;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import you_tube.attach.entity.AttachEntity;
import you_tube.category.entity.CategoryEntity;
import you_tube.channel.ChannelEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "video")
public class VideoEntity {
   @Id
   private String id;
   private String title;
   private String description;
   @Column(name = "category_id")
   private Integer categoryId;
   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "category_id",insertable = false,updatable = false)
   private CategoryEntity category;

   @Column(name = "attach_id")
   private String attachId;
   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "attach_id",insertable = false,updatable = false)
   private AttachEntity attach;

   @Column(name = "preview_attach_id")
   private String previewAttachId;
   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "preview_attach_id",insertable = false,updatable = false)
   private AttachEntity previewAttach;

   @Column(name = "created_date")
   private LocalDateTime createdDate;
   @Column(name = "published_date")
   private LocalDateTime publishedDate;
   @Column(name = "video_status")
   @Enumerated(EnumType.STRING)
   private VideoStatus videoStatus;
   @Column(name = "video_type")
   @Enumerated(EnumType.STRING)
   private VideoType videoType;

   @Column(name = "view_count")
   private Long viewCount;
   @Column(name = "shared_count")
   private Long sharedCount;

   @Column(name = "channel_id")
   private String channelId;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "channel_id")
   private ChannelEntity channel;


}
