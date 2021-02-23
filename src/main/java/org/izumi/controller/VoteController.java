package org.izumi.controller;

import com.alibaba.fastjson.JSONObject;
import groovy.util.logging.Slf4j;
import net.sf.json.util.JSONUtils;
import org.izumi.dao.KmVoteAvgMapper;
import org.izumi.dao.KmVotePwdMapper;
import org.izumi.dao.KmVoteSubMapper;
import org.izumi.dao.VoteMapper;
import org.izumi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author izumi
 * @date 2021-01-21 10:50
 */
@Controller
@Slf4j
public class VoteController {

    @Autowired(required = false)
    private VoteMapper voteMapper;

    @Autowired(required = false)
    private KmVoteSubMapper voteSubMapper;

    @Autowired(required = false)
    private KmVoteAvgMapper voteAvgMapper;

    @Autowired(required = false)
    private KmVotePwdMapper votePwdMapper;

    @RequestMapping("/voteSub1")
    @ResponseBody
    public JSONObject getVote(@RequestBody Map idList){
        List<String> idList1 = (List<String>) idList.get("idList");
        String stringNum = (String) idList.get("bodynum");
        Double bodynum = 0.0;
        if(stringNum == null || "".equals(stringNum)){
            bodynum = 9999.0;
        }else if("9999".equals(stringNum)){
            bodynum = 394.0;
        }else {
            try{
                bodynum = Double.valueOf(stringNum);
            }catch (Exception e){
                bodynum = 394.0;
            }
        }
        List<Vote> list = null;
        List<KmVoteSub> listAdd = null;
        List<String> nameList = new ArrayList<String>();
        List<Integer> subList = new ArrayList<Integer>();
        List<Integer> lengthList = new ArrayList<Integer>();
        List<Double> avgList = new ArrayList<Double>();
        Integer add = 0;
        KmVoteAvg voteAvg = null;
        DecimalFormat df = new DecimalFormat("#.00");
        int sub = 0;
        Double avgAdd = 0.0;
        JSONObject result = new JSONObject();
        if (idList1.size()==0){
            list = voteMapper.selectNullVote(idList1);
        }else{
            list = voteMapper.selectVote(idList1);
            for(Vote vote : list){
                String name = vote.getDocSubject();
                nameList.add(name);
                for (Score score: vote.getScore()){
                    sub+=score.getScore();
                }
                listAdd = voteSubMapper.selectByQId(vote.getId());
                if(listAdd != null && listAdd.size() != 0 && listAdd.get(0) != null){
                    add = listAdd.size();
                    for (KmVoteSub score: listAdd){
                        sub+=score.getFdAnswer();
                    }
                }
                voteAvg = voteAvgMapper.selectByPrimaryKey(vote.getId());
                if(voteAvg != null){
                    avgAdd = voteAvg.getFdAvg();
                }
                //查询添加总数表遍历加总值
                lengthList.add(vote.getScore().size()+add);

                subList.add(sub);
                if(bodynum == 9999.0){
                    avgList.add(Double.valueOf(df.format(sub/(vote.getScore().size()+Double.valueOf(add.toString()))))+avgAdd);
                }else{
                    avgList.add(Double.valueOf(df.format(sub/bodynum))+avgAdd);
                }
                avgAdd=0.0;
                sub=0;
            }
            result.put("name",nameList);
            result.put("sub",subList);
            result.put("avg",avgList);
            result.put("length",lengthList);
        }
        return result;
    }

    @RequestMapping("/voteList")
    @ResponseBody
    public List<Vote> getVoteAll(){
        List<Vote> list = voteMapper.selectAllVote();
        for(Vote vote:list){
            String name = vote.getFdQuestionExplain();
            if("".equals(vote.getFdQuestionExplain())){
                vote.setFdQuestionExplain("空");
            }else{
                vote.setFdQuestionExplain(name.substring(3,name.length()-5));
            }
        }
        return list;
    }

    @RequestMapping("/addSub")
    @ResponseBody
    public JSONObject addSub(@RequestBody Map map){
        JSONObject result = new JSONObject();
        String pwd = (String) map.get("pwd");
        String id = (String) map.get("id");
        if(id == null || "".equals(id)){
            result.put("msg","未选择要添加的对象");
            return result;
        }
        KmVotePwd kmVotePwd = votePwdMapper.selectByPrimaryKey(pwd);
        if(kmVotePwd != null){
            String addsub = (String) map.get("addsub");
            if("".equals(addsub)){
                result.put("msg","输入格式有误");
                return result;
            }else {
                try{
                    Integer sub = Integer.valueOf(addsub);
                    KmVoteSub kmVoteSub = new KmVoteSub();
                    kmVoteSub.setFdQuestionId(id);
                    kmVoteSub.setFdAnswer(sub);
                    voteSubMapper.insertSelective(kmVoteSub);
                    result.put("msg","添加成功");
                    return result;
                }catch (Exception e){
                    result.put("msg","输入格式有误");
                    return result;
                }
            }
        }else{
            result.put("msg","密码错误");
            return result;
        }
    }

    @RequestMapping("/addAvg")
    @ResponseBody
    public JSONObject addAvg(@RequestBody Map map){
        JSONObject result = new JSONObject();
        String pwd = (String) map.get("pwd");
        String id = (String) map.get("id");
        if(id == null || "".equals(id)){
            result.put("msg","未选择要添加的对象");
            return result;
        }
        KmVotePwd kmVotePwd = votePwdMapper.selectByPrimaryKey(pwd);
        if(kmVotePwd != null){
            String addavg = (String) map.get("addavg");
            if("".equals(addavg)){
                result.put("msg","输入格式有误");
                return result;
            }else {
                try{
                    Double avg = Double.valueOf(addavg);
                    KmVoteAvg kmVoteAvg = new KmVoteAvg();
                    kmVoteAvg.setFdId(id);
                    kmVoteAvg.setFdAvg(avg);
                    KmVoteAvg data = voteAvgMapper.selectByPrimaryKey(id);
                    if (data == null) {
                        voteAvgMapper.insertSelective(kmVoteAvg);
                    }else{
                        data.setFdAvg(data.getFdAvg()+avg);
                        voteAvgMapper.updateByPrimaryKey(data);
                    }
                    result.put("msg","添加成功");
                    return result;
                }catch (Exception e){
                    result.put("msg","输入格式有误");
                    return result;
                }
            }
        }else{
            result.put("msg","密码错误");
            return result;
        }
    }

    @RequestMapping("/vote")
    public String vote(){
        return "vote/vote";
    }

    @RequestMapping("/vote1")
    public String vote1(){
        return "vote/vote1";
    }

}
