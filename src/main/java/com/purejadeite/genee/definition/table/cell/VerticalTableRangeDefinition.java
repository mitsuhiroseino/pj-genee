package com.purejadeite.genee.definition.table.cell;

import static com.purejadeite.util.collection.RoughlyMapUtils.*;

import java.util.Map;

import com.purejadeite.genee.definition.table.TableDefinitionInterface;

/**
 * 縦方向に繰り返すセルの読み込み定義です
 * @author mitsuhiroseino
 */
public class VerticalTableRangeDefinition extends AbstractTableRangeDefinition<TableDefinitionInterface<?>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2512534600645058534L;
	
	public static final String CFG_END_ROW = "endRow";

	/**
	 * コンストラクタ
	 *
	 * @param parent
	 *            親定義
	 * @param config
	 *            コンフィグ
	 */
	public VerticalTableRangeDefinition(TableDefinitionInterface<?> parent, Map<String, Object> config) {
		super(parent, config);
		this.end = getIntValue(config, CFG_END_ROW, -1);
	}

	public static boolean assess(TableDefinitionInterface<?> table, Map<String, Object> config) {
		return !config.containsKey(CFG_ROW) && config.containsKey(CFG_COLUMN)
				&& (config.containsKey(CFG_BREAK_VALUE) || config.containsKey(CFG_END_ROW));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> map = super.toMap();
		return map;
	}

	@Override
	protected int toBeginRow(TableDefinitionInterface<?> parent, Map<String, Object> config) {
		return getIntValue(config, CFG_ROW);
	}

	@Override
	protected int toEndRow(TableDefinitionInterface<?> parent, Map<String, Object> config) {
		return getIntValue(config, CFG_END_ROW, UNLIMITED);
	}

	@Override
	protected int toBeginCol(TableDefinitionInterface<?> parent, Map<String, Object> config) {
		return parent.getBegin();
	}

	@Override
	protected int toEndCol(TableDefinitionInterface<?> parent, Map<String, Object> config) {
		return parent.getEnd();
	}
}
