package cn.rongcapital.mc2.me.cpm.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ignite.cache.query.annotations.QuerySqlField;
import org.springframework.data.mongodb.core.mapping.Field;

import cn.rongcapital.mc2.me.cpm.domain.FieldName;

public class CampaignDiagram {

	@QuerySqlField
	@Field(FieldName.FIELD_NODES)
	private List<Map<String, Object>> nodes;

	@QuerySqlField
	@Field(FieldName.FIELD_LINES)
	private List<Map<String, Object>> lines;

	public CampaignDiagram() {}

	public CampaignDiagram(Map<String, Object> node) {
		this.nodes = new ArrayList<Map<String, Object>>();
		this.lines = new ArrayList<Map<String, Object>>();
		this.nodes.add(node);
	}

	/**
	 * 提取节点
	 * @return
	 */
	public List<CampaignNode> parseNodes(long tenantId, String token) {
		return this.nodes.stream().map(node -> {
			String nodeId = (String) node.get("id");
			List<Map<String, Object>> incomings = this.lines.stream().filter(line -> {
				String endId = (String) line.get("end");
				return nodeId.equals(endId);
			}).collect(Collectors.toList());
			List<Map<String, Object>> outcomings = this.lines.stream().filter(line -> {
				String startId = (String) line.get("start");
				return nodeId.equals(startId);
			}).collect(Collectors.toList());
			return new CampaignNode(tenantId, token, node, incomings, outcomings);
		}).collect(Collectors.toList());
	}

	public List<CampaignNode> parseNodes(long tenantId) {
		return this.nodes.stream().map(node -> {
			String nodeId = (String) node.get("id");
			List<Map<String, Object>> incomings = this.lines.stream().filter(line -> {
				String endId = (String) line.get("end");
				return nodeId.equals(endId);
			}).collect(Collectors.toList());
			List<Map<String, Object>> outcomings = this.lines.stream().filter(line -> {
				String startId = (String) line.get("start");
				return nodeId.equals(startId);
			}).collect(Collectors.toList());
			return new CampaignNode(tenantId, node, incomings, outcomings);
		}).collect(Collectors.toList());
	}

	/**
	 * 判断流程图是否有节点
	 * @return
	 */
	public boolean isNodeEmpty() {
		return CollectionUtils.isEmpty(nodes);
	}

	/**
	 * 判断流程图是否有连线
	 * @return
	 */
	public boolean isLineEmpty() {
		return CollectionUtils.isEmpty(lines);
	}

}
