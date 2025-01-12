/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.trino.server;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.airlift.slice.Slice;
import io.airlift.slice.Slices;

import java.io.IOException;

import static com.google.common.io.BaseEncoding.base64;

public final class SliceSerialization
{
    private SliceSerialization() {}

    public static class SliceSerializer
            extends JsonSerializer<Slice>
    {
        @Override
        public void serialize(Slice slice, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException
        {
            String encoded;
            if (slice.hasByteArray()) {
                encoded = base64().encode(slice.byteArray(), slice.byteArrayOffset(), slice.length());
            }
            else {
                encoded = base64().encode(slice.getBytes());
            }
            jsonGenerator.writeString(encoded);
        }
    }

    public static class SliceDeserializer
            extends JsonDeserializer<Slice>
    {
        @Override
        public Slice deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException
        {
            String encoded = jsonParser.readValueAs(String.class);
            return Slices.wrappedBuffer(base64().decode(encoded));
        }
    }
}
