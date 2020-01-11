package com.njust.test.git;

import static org.hamcrest.CoreMatchers.containsString;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.Assert;
import org.junit.Test;

public class GitTest
{
    
    @Test
    public void gitStatus()
        throws IOException, GitAPIException
    {
        File file = getRemoteGitDir();
        
        //git status
        String result = execCommand(file, "git", "status");
        Assert.assertThat(result, containsString("Your branch is up to date with"));
        //System.out.println("[git status]\n" + result);
        
        //git status
        Git git = Git.open(file);
        Status status = git.status().call();
        System.out.println(status.getUntracked());
        System.out.println(status.getAdded());
        
    }
    
    @Test
    public void gitRemoteV_Valid()
        throws IOException
    {
        File file = getRemoteGitDir();
        
        //git remote -v
        String result = execCommand(file, "git", "remote", "-v");
        Assert.assertThat(result, containsString(".git"));
        //System.out.println("[git remote -v]\n" + result);
        
        Git git = Git.open(file);
        String remote = git.getRepository().getConfig().getString("remote", "origin", "url");
        Assert.assertThat(remote, containsString(".git"));
        //System.out.println(remote);
    }
    
    @Test
    public void gitRemoteV_Invalid()
        throws IOException
    {
        File file = getLocalGitDir();
        
        //git remote -v
        String result = execCommand(file, "git", "remote", "-v");
        Assert.assertEquals("", result);
        
        Git git = Git.open(file);
        String remote = git.getRepository().getConfig().getString("remote", "origin", "url");
        Assert.assertNull(remote);
    }
    
    @Test
    public void gitInit_exec()
        throws IOException
    {
        File tmpGitDir = getTmpGitDir();
        String status = execCommand(tmpGitDir, "git", "status");
        Assert.assertThat(status, containsString("not a git repository"));
        
        String initResult = execCommand(tmpGitDir, "git", "init");
        Assert.assertThat(initResult, containsString("Initialized empty Git repository in"));
    }
    
    @Test
    public void gitInit()
        throws IOException, GitAPIException
    {
        File tmpGitDir = getTmpGitDir();
        //check not init
        try
        {
            Git.open(tmpGitDir);
            Assert.fail(
                "should not go here,expect org.eclipse.jgit.errors.RepositoryNotFoundException: repository not found");
        }
        catch (IOException e)
        {
            Assert.assertThat(e.getMessage(), containsString("repository not found"));
        }
        
        //test init
        Assert.assertFalse(new File(tmpGitDir, ".git").exists());
        Git.init().setDirectory(tmpGitDir).call();
        Assert.assertTrue(new File(tmpGitDir, ".git").exists());
    }
    
    /**
     * 这个仓库有对应的远程仓库，可以更新和提交
     */
    private static File getRemoteGitDir()
    {
        return new File("D:\\BACloud\\common-lib");
    }
    
    /**
     * 这是一个本地的git仓库，没有对应的远程仓库的
     */
    private static File getLocalGitDir()
    {
        return new File("D:\\daveCode\\HelloDemo");
    }
    
    /**
     * 这是一个本地的非git仓库，待git init初始化
     */
    private static File getTmpGitDir()
        throws IOException
    {
        File dir = new File("D:\\2222\\gitInitTest");
        if (dir.exists() && dir.isDirectory())
        {
            FileUtils.cleanDirectory(dir);
            return dir;
        }
        return null;
    }
    
    private static String execCommand(File dir, String... command)
        throws IOException
    {
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.directory(dir);
        builder.redirectErrorStream(true);
        Process process = builder.start();
        
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(),
            StandardCharsets.UTF_8));
        
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null)
        {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
}
