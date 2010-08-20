package serializers;

import org.msgpack.*;
import serializers.msgpack.media.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MessagePack
{
	public static void register(TestGroups groups)
	{
		groups.media.add(MediaTransformer, MediaSerializer);
	}

	// ------------------------------------------------------------
	// Serializers

	public static final Serializer<MediaContent> MediaSerializer = new Serializer<MediaContent>()
	{
		public MediaContent deserialize (byte[] array) throws IOException
		{
			Unpacker pac = new Unpacker();
			pac.wrap(array);
			return MediaContent.unpack(pac);
		}

		public byte[] serialize(MediaContent content) throws IOException
		{
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			new Packer(out).pack(content);
			return out.toByteArray();
		}

		public String getName()
		{
			return "msgpack";
		}
	};

	// ------------------------------------------------------------
	// Transformers

	public static final Transformer<data.media.MediaContent,MediaContent> MediaTransformer = new Transformer<data.media.MediaContent,MediaContent>()
	{
		// ----------------------------------------------------------
		// Forward

		public MediaContent forward(data.media.MediaContent mc)
		{
			MediaContent out = new MediaContent();

			out.media = forwardMedia(mc.media);
			for (data.media.Image image : mc.images) {
				out.image.add( forwardImage(image) );
			}

			return out;
		}

		private Media forwardMedia(data.media.Media media)
		{
			Media out = new Media();

			out.uri = media.uri;
			if (media.title != null) out.title = media.title;
			out.width = media.width;
			out.height = media.height;
			out.format = media.format;
			out.duration = media.duration;
			out.size = media.size;
			if (media.hasBitrate) out.bitrate = media.bitrate;
			for (String person : media.persons) {
				out.person.add(person);
			}
			out.player = forwardPlayer(media.player);
			if (media.copyright != null) out.copyright = media.copyright;

			return out;
		}

		public int forwardPlayer(data.media.Media.Player p)
		{
			switch (p) {
				case JAVA: return 0;
				case FLASH: return 1;
				default:
					throw new AssertionError("invalid case: " + p);
			}
		}

		private Image forwardImage(data.media.Image image)
		{
			Image out = new Image();

			out.uri = image.uri;
			if (image.title != null) out.title = image.title;
			out.width = image.width;
			out.height = image.height;
			out.size = forwardSize(image.size);

			return out;
		}

		public int forwardSize(data.media.Image.Size s)
		{
			switch (s) {
				case SMALL: return 0;
				case LARGE: return 1;
				default:
					throw new AssertionError("invalid case: " + s);
			}
		}

		// ----------------------------------------------------------
		// Reverse

		public data.media.MediaContent reverse(MediaContent mc)
		{
			List<data.media.Image> images = new ArrayList<data.media.Image>(mc.image.size());
			for (Image image : mc.image) {
				images.add(reverseImage(image));
			}
			data.media.Media media = reverseMedia(mc.media);
			return new data.media.MediaContent(media, images);
		}

		private data.media.Media reverseMedia(Media media)
		{
			return new data.media.Media(
				media.uri,
				media.title.isEmpty() ? null : media.title,
				media.width,
				media.height,
				media.format,
				media.duration,
				media.size,
				media.bitrate,
				media.bitrate != -1,
				media.person,
				reversePlayer(media.player),
				media.copyright.isEmpty() ? null : media.copyright);
		}

		public data.media.Media.Player reversePlayer(int p)
		{
			switch (p) {
				case 0:  return data.media.Media.Player.JAVA;
				case 1: return data.media.Media.Player.FLASH;
				default:
					throw new AssertionError("invalid case: " + p);
			}
		}

		private data.media.Image reverseImage(Image image)
		{
			return new data.media.Image(
				image.uri,
				image.title.isEmpty() ? null : image.title,
				image.width,
				image.height,
				reverseSize(image.size));
		}

		public data.media.Image.Size reverseSize(int s)
		{
			switch (s) {
				case 0: return data.media.Image.Size.SMALL;
				case 1: return data.media.Image.Size.LARGE;
				default:
					throw new AssertionError("invalid case: " + s);
			}
		}

		public data.media.MediaContent shallowReverse(MediaContent mc)
		{
			data.media.Media media = reverseMedia(mc.media);
			List<data.media.Image> image = Collections.<data.media.Image>emptyList();
			return new data.media.MediaContent(media, image);
		}
	};
}

