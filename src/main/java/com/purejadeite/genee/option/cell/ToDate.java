package com.purejadeite.genee.option.cell;

import static com.purejadeite.util.collection.RoughlyMapUtils.*;

import java.util.Map;

import com.purejadeite.genee.content.ContentInterface;
import com.purejadeite.genee.definition.DefinitionInterface;
import com.purejadeite.genee.input.sxssf.SxssfUtils;

/**
 * 文字列を Date へ変換するクラス
 * @author mitsuhiroseino
 *
 */
public class ToDate extends AbstractStringCellOption {

	private static final long serialVersionUID = 247396559988860716L;

	protected static final String CFG_USE1904 = "use1904";

	protected boolean use1904windowing;

	/**
	 * コンストラクタ
	 * @param cell 値の取得元Cell読み込み定義
	 * @param config コンバーターのコンフィグ
	 */
	public ToDate(DefinitionInterface<?> definition, Map<String, Object> config) {
		super(definition);
		use1904windowing = getBooleanValue(config, CFG_USE1904);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object applyToString(String value, ContentInterface<?, ?> content) {
		return SxssfUtils.getDate(value, use1904windowing);
	}

}
