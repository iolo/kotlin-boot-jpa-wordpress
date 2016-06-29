package kr.iolo.samples

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "wp_posts", indexes = arrayOf(
        Index(name = "post_name", columnList = "post_name"),
        Index(name = "post_type_status_date_id", columnList = "post_type, post_status, post_date, id"),
        Index(name = "post_parent", columnList = "post_parent"),
        Index(name = "post_author", columnList = "post_author")
))
data class Post(
        @Id
        @GeneratedValue
        @Column(name = "id")
        val id: Long = 0,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "post_author")
        val author: User? = null,
        // XXX: wordpress는 FK에 null 대신 0를 넣고 수동 조인...
        //@Column(name = "post_author", nullable = false)
        //val authorId: Long = 0,
        @Column(name = "post_date", nullable = false, columnDefinition = "DATETIME DEFAULT '0000-00-00 00:00:00'")
        val date: LocalDateTime = LocalDateTime.MIN,
        @Column(name = "post_date_gmt", nullable = false, columnDefinition = "DATETIME DEFAULT '0000-00-00 00:00:00'")
        val dateGMT: LocalDateTime = LocalDateTime.MIN,
        @Column(name = "post_content", nullable = false, columnDefinition = "LONGTEXT")
        val content: String = "",
        @Column(name = "post_title", nullable = false, columnDefinition = "TEXT")
        val title: String = "",
        @Column(name = "post_excerpt", nullable = false, columnDefinition = "TEXT")
        val excerpt: String = "",
        @Column(name = "post_status", length = 20, nullable = false)
        val status: String = "publish",
        @Column(name = "comment_status", length = 20, nullable = false)
        val commentStatus: String = "open",
        @Column(name = "ping_status", length = 20, nullable = false)
        val pingStatus: String = "open",
        @Column(name = "post_password", length = 20, nullable = false)
        val password: String = "",
        @Column(name = "post_name", length = 20, nullable = false)
        val name: String = "",
        @Column(name = "to_ping", nullable = false, columnDefinition = "TEXT")
        val toPing: String = "",
        @Column(name = "pinged", nullable = false, columnDefinition = "TEXT")
        val pinged: String = "",
        @Column(name = "post_modified", nullable = false, columnDefinition = "DATETIME DEFAULT '0000-00-00 00:00:00'")
        val modified: LocalDateTime = LocalDateTime.MIN,
        @Column(name = "post_modified_gmt", nullable = false, columnDefinition = "DATETIME DEFAULT '0000-00-00 00:00:00'")
        val modifiedGMT: LocalDateTime = LocalDateTime.MIN,
        @Column(name = "post_content_filtered", columnDefinition = "LONGTEXT")
        val contentFiltered: String = "",
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "post_parent")
        val parent: Post? = null,
        // XXX: wordpress는 FK에 null 대신 0를 넣고 수동 조인...
        //@Column(name = "post_parent", nullable = false)
        //val parentId: Long = 0,
        @Column(name = "guid", length = 255, nullable = false)
        val guid: String = "",
        @Column(name = "menu_order", nullable = false)
        val menuOrder: Int = 0,
        @Column(name = "post_type", length = 20, nullable = false)
        val type: String = "post",
        @Column(name = "post_mime_type", length = 100, nullable = false)
        val mimeType: String = "",
        @Column(name = "comment_count", nullable = false)
        val commentCount: Long = 0,
        //---------------------------------------------------------
        @OneToMany(mappedBy = "post")
        val metas: List<PostMeta> = listOf(),
        @OneToMany(mappedBy = "parent")
        val children: List<Post> = listOf(),
        @OneToMany(mappedBy = "post")
        val comments: List<Comment> = listOf()
        // XXX: wordpress는 한 개의 JoinTable로 post-taxonomy & link-taxonomy 두 개의  many-to-many 수동 조인...
        //@OneToMany(mappedBy = "objectId")
        //val relationships: List<TermRelationship> = listOf()
) {
    // XXX: lazy fetch with circular reference --> infinite recursion
    override fun toString() = "Post(id=$id)"
}

@Entity
@Table(name = "wp_postmeta", indexes = arrayOf(
        Index(name = "postmeta_post_id", columnList = "post_id"),
        Index(name = "postmeta_meta_key", columnList = "meta_key")

))
data class PostMeta(
        @Id
        @GeneratedValue
        @Column(name = "meta_id")
        val id: Long = 0,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "post_id")
        val post: Post? = null,
        // XXX: wordpress는 FK에 null 대신 0를 넣고 수동 조인...
        //@Column(name = "post_id", nullable = false)
        //val postId: Long = 0,
        @Column(name = "meta_key", nullable = true)
        val key: String = "",
        @Column(name = "meta_value", nullable = true)
        val value: String = ""
) {
    // XXX: lazy fetch with circular reference --> infinite recursion
    override fun toString() = "PostMeta(id=$id, post=$post)"
}

@Entity
@Table(name = "wp_comments", indexes = arrayOf(
        Index(name = "comment_post_id", columnList = "comment_post_id"),
        Index(name = "comment_approved_date_gmt", columnList = "comment_approved, comment_date_gmt"),
        Index(name = "comment_date_gmt", columnList = "comment_date_gmt"),
        Index(name = "comment_parent", columnList = "comment_parent"),
        Index(name = "comment_author_email", columnList = "comment_author_email")
))
data class Comment(
        @Id
        @GeneratedValue
        @Column(name = "comment_id")
        val id: Long = 0,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "comment_post_id")
        val post: Post? = null,
        //@JoinColumn(name = "comment_post_id", nullable = false)
        //val postId: Long = 0,
        @Column(name = "comment_author", nullable = false, columnDefinition = "TINYTEXT")
        val author: String = "",
        @Column(name = "comment_author_email", length = 100, nullable = false)
        val authorEmail: String = "",
        @Column(name = "comment_author_url", length = 200, nullable = false)
        val authorUrl: String = "",
        @Column(name = "comment_author_ip", length = 100, nullable = false)
        val authorIp: String = "",
        @Column(name = "comment_date", nullable = false, columnDefinition = "DATETIME DEFAULT '0000-00-00 00:00:00'")
        val date: LocalDateTime = LocalDateTime.MIN,
        @Column(name = "comment_date_gmt", nullable = false, columnDefinition = "DATETIME DEFAULT '0000-00-00 00:00:00'")
        val dateGMT: LocalDateTime = LocalDateTime.MIN,
        @Column(name = "comment_content", nullable = false, columnDefinition = "TEXT")
        val content: String = "",
        @Column(name = "comment_karma", nullable = false)
        val karma: Int = 0,
        @Column(name = "comment_approved", length = 20, nullable = false)
        val approved: String = "1",
        @Column(name = "comment_agent", length = 255, nullable = false)
        val agent: String = "",
        @Column(name = "comment_type", length = 20, nullable = false)
        val type: String = "",
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "comment_parent")
        val parent: Comment? = null,
        // XXX: wordpress는 FK에 null 대신 0를 넣고 수동 조인...
        //@Column(name = "comment_parent", nullable = false)
        //val parentId: Long = 0,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        val user: User? = null,
        // XXX: wordpress는 FK에 null 대신 0를 넣고 수동 조인...
        //@Column(name = "user_id", nullable = false)
        //val userId: Long = 0,
        //---------------------------------------------------------
        @OneToMany(mappedBy = "comment")
        val metas: List<CommentMeta> = listOf(),
        @OneToMany(mappedBy = "parent")
        val children: List<Comment> = listOf()
) {
    // XXX: lazy fetch with circular reference --> infinite recursion
    override fun toString() = "Comment(id=$id)"
}

@Entity
@Table(name = "wp_commentmeta", indexes = arrayOf(
        Index(name = "commentmeta_comment_id", columnList = "comment_id"),
        Index(name = "commentmeta_meta_key", columnList = "meta_key")
))
data class CommentMeta(
        @Id
        @GeneratedValue
        @Column(name = "meta_id")
        val id: Long = 0,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "comment_id")
        val comment: Comment? = null,
        // XXX: wordpress는 FK에 null 대신 0를 넣고 수동 조인...
        //@Column(name = "comment_id", nullable = false)
        //val commentId: Long = 0,
        @Column(name = "meta_key", nullable = true)
        val key: String = "",
        @Column(name = "meta_value", nullable = true, columnDefinition = "LONGTEXT")
        val value: String = ""
) {
    // XXX: lazy fetch with circular reference --> infinite recursion
    override fun toString() = "CommentMeta(id=$id, comment=$comment)"
}

@Entity
@Table(name = "wp_links", indexes = arrayOf(
        Index(name = "link_visible", columnList = "link_visible")
))
data class Link(
        @Id
        @GeneratedValue
        @Column(name = "link_id")
        val id: Long = 0,
        @Column(name = "link_url", length = 255, nullable = false)
        val url: String = "",
        @Column(name = "link_name", length = 255, nullable = false)
        val name: String = "",
        @Column(name = "link_image", length = 255, nullable = false)
        val image: String = "",
        @Column(name = "link_target", length = 25, nullable = false)
        val target: String = "",
        @Column(name = "link_description", length = 255, nullable = false)
        val description: String = "",
        @Column(name = "link_visible", length = 20, nullable = false)
        val visible: String = "Y",
        @Column(name = "link_owner", nullable = false)
        val owner: Long = 1,
        @Column(name = "link_rating", nullable = false)
        val rating: Int = 0,
        @Column(name = "link_updated", nullable = false, columnDefinition = "DATETIME DEFAULT '0000-00-00 00:00:00'")
        val updated: LocalDateTime = LocalDateTime.MIN,
        @Column(name = "link_rel", length = 255, nullable = false)
        val rel: String = "",
        @Column(name = "link_notes", columnDefinition = "MEDIUMTEXT")
        val notes: String = "",
        @Column(name = "link_rss", length = 255, nullable = false)
        val rss: String = ""
        //---------------------------------------------------------
        // XXX: wordpress는 한 개의 JoinTable로 post-taxonomy & link-taxonomy 두 개의  many-to-many 수동 조인...
        //@OneToMany(mappedBy = "objectId")
        //val relationships: List<TermRelationship> = listOf()
)

@Entity
@Table(name = "wp_users", indexes = arrayOf(
        Index(name = "user_login", columnList = "user_login"),
        Index(name = "user_nicename", columnList = "user_nicename")
))
data class User(
        @Id
        @GeneratedValue
        @Column(name = "id")
        val id: Long = 0,
        @Column(name = "user_login", length = 60, nullable = false)
        val login: String = "",
        @Column(name = "user_pass", length = 64, nullable = false)
        val password: String = "",
        @Column(name = "user_nicename", length = 50, nullable = false)
        val niceName: String = "",
        @Column(name = "user_email", length = 100, nullable = false)
        val email: String = "",
        @Column(name = "user_url", length = 100, nullable = false)
        val url: String = "",
        @Column(name = "user_registered", nullable = false, columnDefinition = "DATETIME DEFAULT '0000-00-00 00:00:00'")
        val registered: LocalDateTime = LocalDateTime.MIN,
        @Column(name = "user_activation_key", length = 60, nullable = false)
        val activationKey: String = "",
        @Column(name = "user_status", nullable = false)
        val status: Int = 0,
        @Column(name = "display_name", length = 250, nullable = false)
        val displayName: String = "",
        //---------------------------------------------------------
        @OneToMany(mappedBy = "user")
        val metas: List<UserMeta> = listOf(),
        @OneToMany(mappedBy = "author")
        val posts: List<Post> = listOf(),
        @OneToMany(mappedBy = "user")
        val comments: List<Comment> = listOf()
) {
    // XXX: lazy fetch with circular reference --> infinite recursion
    override fun toString() = "User(id=$id)"
}

@Entity
@Table(name = "wp_usermeta", indexes = arrayOf(
        Index(name = "usermeta_user_id", columnList = "user_id"),
        Index(name = "usermeta_meta_key", columnList = "meta_key")
))
data class UserMeta(
        @Id
        @GeneratedValue
        @Column(name = "umeta_id", nullable = false)
        val id: Long = 0,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        val user: User? = null,
        // XXX: wordpress는 FK에 null 대신 0를 넣고 수동 조인...
        //@Column(name = "user_id", nullable = false)
        //val userId: Long = 0,
        @Column(name = "meta_key", length = 255, nullable = true)
        val key: String = "",
        @Column(name = "meta_value", nullable = true, columnDefinition = "LONGTEXT")
        val value: String = ""
) {
    // XXX: lazy fetch with circular reference --> infinite recursion
    override fun toString() = "UserMeta(id=$id, user=$user)"
}

@Entity
@Table(name = "wp_options", indexes = arrayOf(
        Index(name = "option_name", unique = true, columnList = "option_name")
))
data class Option(
        @Id
        @GeneratedValue
        @Column(name = "option_id")
        val id: Long = 0,
        @Column(name = "option_name", length = 64, unique = true, nullable = false)
        val name: String = "",
        @Column(name = "option_value", nullable = false, columnDefinition = "LONGTEXT")
        val value: String = "",
        @Column(name = "autoload", length = 20, nullable = false)
        val autoload: String = "yes"
)

@Entity
@Table(name = "wp_terms", indexes = arrayOf(
        Index(name = "term_slug", unique = true, columnList = "slug"),
        Index(name = "term_name", columnList = "name")
))
data class Term(
        @Id
        @GeneratedValue
        @Column(name = "term_id")
        val id: Long = 0,
        @Column(name = "name", length = 200, nullable = false)
        val name: String = "",
        @Column(name = "slug", length = 191, nullable = false) // @@iolo: 200->191
        val slug: String = "",
        @Column(name = "term_group", nullable = false)
        val group: Long = 0,
        //---------------------------------------------------------
        @OneToMany(mappedBy = "term")
        val metas: List<TermMeta> = listOf(),
        @OneToMany(mappedBy = "term")
        val taxonomies: List<TermTaxonomy> = listOf()
) {
    // XXX: lazy fetch with circular reference --> infinite recursion
    override fun toString() = "Term(id=$id)"
}

@Entity
@Table(name = "wp_termmeta", indexes = arrayOf(
        Index(name = "termmeta_term_id", columnList = "term_id"),
        Index(name = "termmeta_meta_key", columnList = "meta_key")
))
data class TermMeta(
        @Id
        @GeneratedValue
        @Column(name = "meta_id")
        val id: Long = 0,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "term_id")
        val term: Term? = null,
        // XXX: wordpress는 FK에 null 대신 0를 넣고 수동 조인...
        //@Column(name="term_id", nullable = false)
        //val termId: Long = 0,
        @Column(name = "meta_key", nullable = true)
        val key: String = "",
        @Column(name = "meta_value", nullable = true, columnDefinition = "LONGTEXT")
        val value: String = ""
) {
    // XXX: lazy fetch with circular reference --> infinite recursion
    override fun toString() = "TermMeta(id=$id, term=$term)"
}

@Entity
@Table(name = "wp_term_taxonomy", indexes = arrayOf(
        Index(name = "term_taxonomy_id_taxonomy", unique = true, columnList = "term_id, taxonomy"),
        Index(name = "term_taxonomy_taxonomy", columnList = "taxonomy")
))
data class TermTaxonomy(
        @Id
        @GeneratedValue
        @Column(name = "term_taxonomy_id")
        val id: Long = 0,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "term_id")
        val term: Term? = null,
        // XXX: wordpress는 FK에 null 대신 0를 넣고 수동 조인...
        //@Column(name = "term_id", nullable = false)
        //val termId: Long = 0
        @Column(name = "taxonomy", length = 32, nullable = false)
        val taxonomy: String = "",
        @Column(name = "description", nullable = false, columnDefinition = "LONGTEXT")
        val description: String = "",
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "parent")
        val parent: TermTaxonomy? = null,
        // XXX: wordpress는 FK에 null 대신 0를 넣고 수동 조인...
        //@Column(name = "parent", nullable = false)
        //val parentId: Long = 0,
        @Column(name = "count", nullable = false)
        val count: Long = 0,
        //---------------------------------------------------------
        // XXX: wordpress는 한 개의 JoinTable로 post-taxonomy & link-taxonomy 두 개의  many-to-many 수동 조인...
        @OneToMany(mappedBy = "taxonomy")
        val relationships: List<TermRelationship> = listOf()
) {
    // XXX: lazy fetch with circular reference --> infinite recursion
    override fun toString() = "TermTaxonomy(id=$id, term=$term)"
}

@Entity
@IdClass(TermRelationshipPK::class)
@Table(name = "wp_term_relationships", indexes = arrayOf(
        Index(name = "term_relationship_term_taxonomy_id", columnList = "term_taxonomy_id")
))
data class TermRelationship(
        @Id
        @Column(name = "object_id", nullable = false)
        val objectId: Long = 0,
        // XXX: wordpress는 한 개의 JoinTable로 post-taxonomy & link-taxonomy 두 개의  many-to-many 수동 조인...
        //@ManyToOne(fetch = FetchType.LAZY)
        //@JoinColumn(name = "object_id")
        //val post: Post? = null,
        //@ManyToOne(fetch = FetchType.LAZY)
        //@JoinColumn(name = "object_id")
        //val link: Link? = null,
        @Id
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "term_taxonomy_id")
        val taxonomy: TermTaxonomy? = null,
        // XXX: wordpress는 FK에 null 대신 0를 넣고 수동 조인...
        //@Column(name = "term_taxonomy_id", nullable = false)
        //val taxonomyId: Long = 0,
        @Column(name = "term_order")
        val order: Int = 0
) {
    // XXX: lazy fetch with circular reference --> infinite recursion
    override fun toString() = "TermRelationship(objectId=$objectId, taxonomy=$taxonomy)"
}

data class TermRelationshipPK(
        val objectId: Long = 0,
        val taxonomy: Long = 0
) : Serializable;
