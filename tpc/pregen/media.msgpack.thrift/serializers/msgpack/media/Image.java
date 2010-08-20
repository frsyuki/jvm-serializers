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

public class Image implements MessagePackable, MessageUnpackable, MessageConvertable {

	public int size;
	public String uri;
	public String title;
	public int width;
	public int height;

	public Image() {
		this.size = 0;
		this.uri = "";
		this.title = "";
		this.width = 0;
		this.height = 0;
	}

	public static Image unpack(Unpacker _Pac) throws IOException {
		Image obj = new Image();
		obj.messageUnpack(_Pac);
		return obj;
	}

	public static Image convert(Object deserialized) {
		Image obj = new Image();
		obj.messageConvert(deserialized);
		return obj;
	}

	public void messagePack(Packer _Pk) throws IOException {
		_Pk.packArray(5);
		_Pk.pack(uri);
		_Pk.pack(title);
		_Pk.pack(width);
		_Pk.pack(height);
		_Pk.pack(size);
	}

	public void messageUnpack(Unpacker _Pac) throws IOException, MessageTypeException {
		int _Length = _Pac.unpackArray();

		if(_Length < 5) {
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
		this.size = _Pac.unpackInt();

		for(int i=5; i < _Length; i++) {
			_Pac.unpackObject();
		}
	}

	public void messageConvert(Object _Obj) throws MessageTypeException {
		if(!(_Obj instanceof List)) {
			throw new MessageTypeException();
		}
		List _Array = (List)_Obj;
		int _Length = _Array.size();

		if(_Length < 5) {
			throw new MessageTypeException();
		}


			Object _A1 = _Array.get(1);
			if(_A1 != null) {
				this.uri = StringSchema.convertString(_A1);
			}


			Object _A2 = _Array.get(2);
			if(_A2 != null) {
				this.title = StringSchema.convertString(_A2);
			}


			this.width = IntSchema.convertInt(_Array.get(3));


			this.height = IntSchema.convertInt(_Array.get(4));


			this.size = IntSchema.convertInt(_Array.get(5));

	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof Image) {
			return this.equals((Image)o);
		}
		return false;
	}

	public boolean equals(Image o) {
		if(o == null) {
			return false;
		}
		if(!(size == o.size)) {
			return false;
		}
		if(!(uri == null ? (o == null) : uri.equals(o.uri))) {
			return false;
		}
		if(!(title == null ? (o == null) : title.equals(o.title))) {
			return false;
		}
		if(!(width == o.width)) {
			return false;
		}
		if(!(height == o.height)) {
			return false;
		}
		return true;
	}
}

