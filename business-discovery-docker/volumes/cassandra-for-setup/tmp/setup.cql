-- キースペースを作成
DROP KEYSPACE IF EXISTS keyspace_name;
CREATE KEYSPACE keyspace_name
 WITH REPLICATION = {
  'class' : 'NetworkTopologyStrategy',
  'datacenter1' : 1
 } ;

-- 作成したキースペースを選択
use keyspace_name;

-- カラムファミリを作成
DROP COLUMNFAMILY IF EXISTS video;
CREATE COLUMNFAMILY video
(
  Id VARCHAR PRIMARY KEY
  ,Title VARCHAR
  ,ChannelId VARCHAR
  ,ChannelTitle VARCHAR
  ,DefaultAudioLanguage VARCHAR
  ,PublishedAt VARCHAR
  ,CommentCount varint
  ,FavoriteCount varint
  ,LikeCount varint
  ,DislikeCount varint
  ,ViewCount varint
);
