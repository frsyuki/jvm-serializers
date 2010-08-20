package serializers.msgpack.media;

import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.io.IOException;
import org.msgpack.Packer;
import org.msgpack.Unpacker;
import org.msgpack.MessagePackable;
import org.msgpack.MessageUnpackable;
import org.msgpack.MessageConvertable;
import org.msgpack.MessageTypeException;
import org.msgpack.Schema;
import org.msgpack.schema.*;

public class MediaContent implements MessagePackable, MessageUnpackable, MessageConvertable {

	public List<Image> image;
	public Media media;

	public MediaContent() {
		this.image = new ArrayList(0);
		this.media = new Media();
	}

	public static MediaContent unpack(Unpacker _Pac) throws IOException {
		MediaContent obj = new MediaContent();
		obj.messageUnpack(_Pac);
		return obj;
	}

	public static MediaContent convert(Object deserialized) {
		MediaContent obj = new MediaContent();
		obj.messageConvert(deserialized);
		return obj;
	}

	public void messagePack(Packer _Pk) throws IOException {
		_Pk.packArray(2);
		_Pk.pack(image);
		_Pk.pack(media);
	}

	public void messageUnpack(Unpacker _Pac) throws IOException, MessageTypeException {
		int _Length = _Pac.unpackArray();

		if(_Length < 2) {
			throw new MessageTypeException();
		}

		int _A1 = _Pac.unpackArray();
		this.image = new ArrayList(_A1);
		for(int _A2=0; _A2 < _A1; _A2++) {
		Image _A3 = new Image();
		_A3.messageUnpack(_Pac);
		this.image.add(_A3);
		}
		this.media = new Media();
		this.media.messageUnpack(_Pac);

		for(int i=2; i < _Length; i++) {
			_Pac.unpackObject();
		}
	}

	public void messageConvert(Object _Obj) throws MessageTypeException {
		if(!(_Obj instanceof List)) {
			throw new MessageTypeException();
		}
		List _Array = (List)_Obj;
		int _Length = _Array.size();

		if(_Length < 2) {
			throw new MessageTypeException();
		}


			this.image = ListSchema.convertList(_Array.get(1), new UserClassSchema(new Image()), null);


			this.media = new Media();
this.media.messageConvert(_Array.get(2));

	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof MediaContent) {
			return this.equals((MediaContent)o);
		}
		return false;
	}

	public boolean equals(MediaContent o) {
		if(o == null) {
			return false;
		}
		if(!(image == null ? (o == null) : image.equals(o.image))) {
			return false;
		}
		if(!(media == null ? (o == null) : media.equals(o.media))) {
			return false;
		}
		return true;
	}
}

