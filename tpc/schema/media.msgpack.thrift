namespace java serializers.msgpack.media

typedef i32 int
typedef i64 long

/**
 * Some comment...
 */
struct Image {
  1: string uri,              //url to the images
  2: optional string title,
  3: required int width,
  4: required int height,
  5: required int size,
}

struct Media {
  1: string uri,             //url to the thumbnail
  2: optional string title,
  3: required int width,
  4: required int height,
  5: required string format,
  6: required long duration,
  7: required long size,
  8: optional int bitrate = -1,
  9: required list<string> person,
  10: required int player,
  11: optional string copyright,
}

struct MediaContent {
  1: required list<Image> image,
  2: required Media media,
}
