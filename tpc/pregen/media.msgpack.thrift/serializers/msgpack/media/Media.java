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

public class Media implements MessagePackable, MessageUnpackable, MessageConvertable {

	public String format;
	public String copyright;
	public long duration;
	public String uri;
	public long size;
	public String title;
	public int bitrate;
	public int width;
	public List<String> person;
	public int height;
	public int player;

	public Media() {
		this.format = "";
		this.copyright = "";
		this.duration = 0;
		this.uri = "";
		this.size = 0;
		this.title = "";
		this.bitrate = -1;
		this.width = 0;
		this.person = new ArrayList(0);
		this.height = 0;
		this.player = 0;
	}

	public static Media unpack(Unpacker _Pac) throws IOException {
		Media obj = new Media();
		obj.messageUnpack(_Pac);
		return obj;
	}

	public static Media convert(Object deserialized) {
		Media obj = new Media();
		obj.messageConvert(deserialized);
		return obj;
	}

	public void messagePack(Packer _Pk) throws IOException {
		_Pk.packArray(11);
		_Pk.pack(uri);
		_Pk.pack(title);
		_Pk.pack(width);
		_Pk.pack(height);
		_Pk.pack(format);
		_Pk.pack(duration);
		_Pk.pack(size);
		_Pk.pack(bitrate);
		_Pk.pack(person);
		_Pk.pack(player);
		_Pk.pack(copyright);
	}

	public void messageUnpack(Unpacker _Pac) throws IOException, MessageTypeException {
		int _Length = _Pac.unpackArray();

		if(_Length < 10) {
			throw new MessageTypeException();
		}

			if(!_Pac.tryUnpackNull()) {
		this.uri = _Pac.unpackString();
			}
			if(!_Pac.tryUnpackNull()) {
		this.title = _Pac.unpackString();
			}
		this.width = _Pac.unpackInt();
		this.height = _Pac.unpackInt();
		this.format = _Pac.unpackString();
		this.duration = _Pac.unpackLong();
		this.size = _Pac.unpackLong();
		this.bitrate = _Pac.unpackInt();
		int _A1 = _Pac.unpackArray();
		this.person = new ArrayList(_A1);
		for(int _A2=0; _A2 < _A1; _A2++) {
		String _A3 = _Pac.unpackString();
		this.person.add(_A3);
		}
		this.player = _Pac.unpackInt();
			if(_Length > 10) {
		this.copyright = _Pac.unpackString();
			}

		for(int i=11; i < _Length; i++) {
			_Pac.unpackObject();
		}
	}

	public void messageConvert(Object _Obj) throws MessageTypeException {
		if(!(_Obj instanceof List)) {
			throw new MessageTypeException();
		}
		List _Array = (List)_Obj;
		int _Length = _Array.size();

		if(_Length < 10) {
			throw new MessageTypeException();
		}


			Object _A4 = _Array.get(1);
			if(_A4 != null) {
				this.uri = StringSchema.convertString(_A4);
			}


			Object _A5 = _Array.get(2);
			if(_A5 != null) {
				this.title = StringSchema.convertString(_A5);
			}


			this.width = IntSchema.convertInt(_Array.get(3));


			this.height = IntSchema.convertInt(_Array.get(4));


			this.format = StringSchema.convertString(_Array.get(5));


			this.duration = LongSchema.convertLong(_Array.get(6));


			this.size = LongSchema.convertLong(_Array.get(7));


			this.bitrate = IntSchema.convertInt(_Array.get(8));


			this.person = ListSchema.convertList(_Array.get(9), new StringSchema(), null);


			this.player = IntSchema.convertInt(_Array.get(10));


			if(_Length <= 10) { return; }
			Object _A6 = _Array.get(11);
			if(_A6 != null) {
				this.copyright = StringSchema.convertString(_A6);
			}

	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof Media) {
			return this.equals((Media)o);
		}
		return false;
	}

	public boolean equals(Media o) {
		if(o == null) {
			return false;
		}
		if(!(format == null ? (o == null) : format.equals(o.format))) {
			return false;
		}
		if(!(copyright == null ? (o == null) : copyright.equals(o.copyright))) {
			return false;
		}
		if(!(duration == o.duration)) {
			return false;
		}
		if(!(uri == null ? (o == null) : uri.equals(o.uri))) {
			return false;
		}
		if(!(size == o.size)) {
			return false;
		}
		if(!(title == null ? (o == null) : title.equals(o.title))) {
			return false;
		}
		if(!(bitrate == o.bitrate)) {
			return false;
		}
		if(!(width == o.width)) {
			return false;
		}
		if(!(person == null ? (o == null) : person.equals(o.person))) {
			return false;
		}
		if(!(height == o.height)) {
			return false;
		}
		if(!(player == o.player)) {
			return false;
		}
		return true;
	}
}

