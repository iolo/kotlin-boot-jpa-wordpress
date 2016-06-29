INSERT INTO wp_users
(id, user_login, user_pass, user_nicename, user_email, user_url, user_registered, user_activation_key, user_status, display_name)
VALUES
  (1, 'login1', 'pass1', 'nice1', 'email1', 'url1', now(), 'actkey1', 11, 'display1'),
  (2, 'login2', 'pass2', 'nice2', 'email2', 'url2', now(), 'actkey2', 22, 'display2'),
  (3, 'login3', 'pass3', 'nice3', 'email3', 'url3', now(), 'actkey3', 33, 'display3'),
  (4, 'login4', 'pass4', 'nice4', 'email4', 'url4', now(), 'actkey4', 44, 'display4');

INSERT INTO wp_usermeta
(umeta_id, user_id, meta_key, meta_value)
VALUES
  (1, 1, 'key1', 'value1'),
  (2, 1, 'key2', 'value2'),
  (3, 1, 'key3', 'value3'),
  (4, 2, 'key4', 'value4'),
  (5, 2, 'key5', 'value5'),
  (6, 3, 'key6', 'value6'),
  (7, 4, 'key7', 'value7');

INSERT INTO wp_links
(link_id, link_url, link_name, link_image, link_target, link_description, link_visible, link_owner, link_rating,
 link_updated, link_rel, link_notes, link_rss)
VALUES
  (1, 'url1', 'name1', 'image1', 'target1', 'descr1', 'visible1', 11, 111, now(), 'rel1', 'notes1', 'rss1'),
  (2, 'url2', 'name2', 'image2', 'target2', 'descr2', 'visible2', 22, 222, now(), 'rel2', 'notes2', 'rss2'),
  (3, 'url3', 'name3', 'image3', 'target3', 'descr3', 'visible3', 33, 333, now(), 'rel3', 'notes3', 'rss3'),
  (4, 'url4', 'name4', 'image4', 'target4', 'descr4', 'visible4', 44, 444, now(), 'rel4', 'notes4', 'rss4');

INSERT INTO wp_options
(option_id, option_name, option_value, autoload)
VALUES
  (1, 'opt1', 'val1', 'yes'),
  (2, 'opt2', 'val2', 'yes'),
  (3, 'opt3', 'val3', 'yes'),
  (4, 'opt4', 'val4', 'yes');

INSERT INTO wp_posts
(id, post_author, post_date, post_date_gmt, post_content, post_title, post_excerpt, post_status,
 comment_status, ping_status, post_password, post_name, to_ping, pinged, post_modified, post_modified_gmt,
 post_content_filtered, post_parent, guid, menu_order, post_type, post_mime_type, comment_count)
VALUES
  (1, 1, now(), now(), 'content1', 'title1', 'excerpt1', 'publish', 'open', 'open', 'pass1', 'name1'
    , 'toping1', 'pinged1', now(), now(), 'filtered1', NULL, 'guid1', 11, 'post', 'mime1', 111),
  (2, 2, now(), now(), 'content2', 'title2', 'excerpt2', 'publish', 'open', 'open', 'pass2', 'name2'
    , 'toping2', 'pinged2', now(), now(), 'filtered2', 1, 'guid2', 22, 'post', 'mime2', 222),
  (3, 3, now(), now(), 'content3', 'title3', 'excerpt3', 'publish', 'open', 'open', 'pass3', 'name3'
    , 'toping3', 'pinged3', now(), now(), 'filtered3', 1, 'guid3', 33, 'post', 'mime3', 333),
  (4, 4, now(), now(), 'content4', 'title4', 'excerpt4', 'publish', 'open', 'open', 'pass4', 'name4'
    , 'toping4', 'pinged4', now(), now(), 'filtered4', 2, 'guid4', 44, 'post', 'mime4', 444);

INSERT INTO wp_postmeta
(meta_id, post_id, meta_key, meta_value)
VALUES
  (1, 1, 'key1', 'value1'),
  (2, 1, 'key2', 'value2'),
  (3, 1, 'key3', 'value3'),
  (4, 2, 'key4', 'value4'),
  (5, 2, 'key5', 'value5'),
  (6, 3, 'key6', 'value6'),
  (7, 4, 'key7', 'value7');

INSERT INTO wp_comments
(comment_id, comment_post_id, comment_author, comment_author_email, comment_author_url, comment_author_ip, comment_date,
 comment_date_gmt, comment_content, comment_karma, comment_approved, comment_agent, comment_type, comment_parent, user_id)
VALUES
  (1, 1, 'author1', 'email1', 'url1', 'ip1', now(), now(), 'content1', 11, '111', 'agent1', 'type1', NULL, 1),
  (2, 1, 'author2', 'email2', 'url2', 'ip2', now(), now(), 'content2', 22, '222', 'agent2', 'type2', NULL, 2),
  (3, 1, 'author3', 'email3', 'url3', 'ip3', now(), now(), 'content3', 33, '333', 'agent3', 'type3', NULL, 3),
  (4, 2, 'author4', 'email4', 'url4', 'ip4', now(), now(), 'content4', 44, '444', 'agent4', 'type4', NULL, 4),
  (5, 2, 'author5', 'email5', 'url5', 'ip5', now(), now(), 'content5', 55, '555', 'agent5', 'type5', 1, NULL),
  (6, 3, 'author6', 'email6', 'url6', 'ip6', now(), now(), 'content6', 66, '666', 'agent6', 'type6', 2, NULL),
  (7, 4, 'author7', 'email7', 'url7', 'ip7', now(), now(), 'content7', 77, '777', 'agent7', 'type7', 5, NULL);

INSERT INTO wp_commentmeta
(meta_id, comment_id, meta_key, meta_value)
VALUES
  (1, 1, 'key1', 'value1'),
  (2, 1, 'key2', 'value2'),
  (3, 1, 'key3', 'value3'),
  (4, 2, 'key4', 'value4'),
  (5, 2, 'key5', 'value5'),
  (6, 3, 'key6', 'value6'),
  (7, 4, 'key7', 'value7');

INSERT INTO wp_terms
(term_id, name, slug, term_group)
VALUES
  (1, 'name1', 'slug1', 11),
  (2, 'name2', 'slug2', 22),
  (3, 'name3', 'slug3', 33),
  (4, 'name4', 'slug4', 44);

INSERT INTO wp_termmeta
(meta_id, term_id, meta_key, meta_value)
VALUES
  (1, 1, 'key1', 'value1'),
  (2, 1, 'key2', 'value2'),
  (3, 1, 'key3', 'value3'),
  (4, 2, 'key4', 'value4'),
  (5, 2, 'key5', 'value5'),
  (6, 3, 'key6', 'value6'),
  (7, 4, 'key7', 'value7');

INSERT INTO wp_term_taxonomy
(term_taxonomy_id, term_id, taxonomy, description, parent, count)
VALUES
  (1, 1, 'taxonomy1', 'descr1', NULL, 11),
  (2, 2, 'taxonomy2', 'descr2', 1, 22),
  (3, 3, 'taxonomy3', 'descr3', 1, 33),
  (4, 4, 'taxonomy4', 'descr4', 2, 44);

INSERT INTO wp_term_relationships
(object_id, term_taxonomy_id, term_order)
VALUES
  (1, 4, 14),
  (1, 3, 13),
  (1, 2, 12),
  (2, 2, 22),
  (2, 1, 21),
  (3, 1, 31),
  (4, 1, 41);
