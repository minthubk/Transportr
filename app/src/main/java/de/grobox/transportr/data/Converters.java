package de.grobox.transportr.data;


import android.arch.persistence.room.TypeConverter;
import android.support.annotation.Nullable;

import java.util.Date;
import java.util.Set;

import de.schildbach.pte.NetworkId;
import de.schildbach.pte.dto.LocationType;
import de.schildbach.pte.dto.Product;

public class Converters {

	@TypeConverter
	public static String fromNetworkId(@Nullable NetworkId networkId) {
		if (networkId == null) return null;
		return networkId.name();
	}

	@Nullable
	@TypeConverter
	public static NetworkId toNetworkId(String network) {
		try {
			return NetworkId.valueOf(network);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@TypeConverter
	public static String fromLocationType(LocationType locationType) {
		return locationType.name();
	}

	@TypeConverter
	public static LocationType toLocationType(String type) {
		try {
			return LocationType.valueOf(type);
		} catch (IllegalArgumentException e) {
			return LocationType.ANY;
		}
	}

	@Nullable
	@TypeConverter
	public static String fromProducts(Set<Product> products) {
		if (products == null) return null;
		return String.valueOf(Product.toCodes(products));
	}

	@TypeConverter
	public static Set<Product> toProducts(@Nullable String codes) {
		if (codes == null) return null;
		return Product.fromCodes(codes.toCharArray());
	}

	@TypeConverter
	public static Date toDate(Long value) {
		return value == null ? null : new Date(value);
	}

	@TypeConverter
	public static Long fromDate(Date date) {
		return date == null ? null : date.getTime();
	}

}
