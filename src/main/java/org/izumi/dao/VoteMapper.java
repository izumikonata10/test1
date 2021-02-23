package org.izumi.dao;

import org.izumi.model.Vote;

import java.util.List;

/**
 * @author izumi
 * @date 2021-01-21 11:02
 */
public interface VoteMapper {

    List<Vote> selectNullVote(List<String> list);

    List<Vote> selectVote(List<String> list);

    List<Vote> selectVoteAdd(String mainid);

    List<Vote> selectAllVote();
}
