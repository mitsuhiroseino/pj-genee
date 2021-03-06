package com.purejadeite.genee.option.cell;

import static com.purejadeite.util.collection.RoughlyMapUtils.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.purejadeite.genee.content.ContentInterface;
import com.purejadeite.genee.definition.DefinitionInterface;

/**
 * 文字列を適切な型に変換するクラス
 * @author mitsuhiroseino
 *
 */
public class Convert extends AbstractStringCellOption {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6354888457620461346L;

	private static final Pattern RE = Pattern.compile("^((0)|((-|)(0\\.[0-9]*[1-9])|((-|)[1-9][0-9]*(\\.[0-9]*[1-9])?)))$");

	protected static final String CFG_NULL_VALUE = "nullValue";

	protected static final String CFG_EMPTY_VALUE = "emptyValue";

	/**
	 * nullとして扱う文字列
	 */
	protected String nullValue;

	/**
	 * 空文字として扱う文字列
	 */
	protected String emptyValue;


	/**
	 * コンストラクタ
	 * @param cell 値の取得元Cell読み込み定義
	 * @param config コンバーターのコンフィグ
	 */
	public Convert(DefinitionInterface<?> definition, Map<String, Object> config) {
		super(definition);
		nullValue = getString(config, CFG_NULL_VALUE, "null");
		emptyValue = getString(config, CFG_EMPTY_VALUE, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object applyToString(String value, ContentInterface<?, ?> content) {
		if ((nullValue == null && value == null) || (nullValue != null && nullValue.equals(value))) {
			return null;
		} else if ((emptyValue == null && value == null) || (emptyValue != null && emptyValue.equals(value))) {
				return "";
		} else  if ("true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value)) {
			return Boolean.valueOf(value);
		} else if (isNumber(value)) {
			return new BigDecimal(value);
		} else {
			return value;
		}

	}
	private boolean isNumber(String value) {
		Matcher matcher = RE.matcher(value);
		return matcher.find();
	}
}
