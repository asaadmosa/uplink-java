package io.storj;

/**
 * Options for listing buckets.
 *
 * @see Project#listBuckets(BucketListOption...)
 */
public class BucketListOption {

    private enum Key {
        CURSOR,
    }

    private Key key;

    private Object value;

    BucketListOption(Key key, Object value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Option for the starting cursor in the bucket listing. The name of the first bucket in the
     * result listing will be <b>after</b> the cursor.
     *
     * @param cursor a {@link String} with starting cursor
     * @return a {@link BucketListOption}
     */
    public static BucketListOption cursor(String cursor) {
        return new BucketListOption(Key.CURSOR, cursor);
    }


    static JNAUplink.ListBucketsOptions.ByReference internal(BucketListOption... options) {
        JNAUplink.ListBucketsOptions.ByReference listOptions = new JNAUplink.ListBucketsOptions.ByReference();
        for (BucketListOption option : options) {
            if (option.key == Key.CURSOR) {
                listOptions.cursor = option.value.toString();
            }
        }

        return listOptions;
    }
}