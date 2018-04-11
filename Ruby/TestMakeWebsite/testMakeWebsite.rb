require_relative "makeWebsite"
require "minitest/autorun"

class TestMakeWebsite < MiniTest::Test

  def setup
    @file_name = "resume.html"
    @my_lines = ["Bo Liu\n", "\n", "University of Pennsylvania, Philadelphia, PA, USA  -  Master of Science in Electrical Engineering\n", "\n", "University of California, Los Angeles, CA, USA  - Bachelor of Science in Telecommunication Engineering\n", "\n", "boliu@seas.upenn.edu\n", "\n", "Courses - : Programming Languages and Techniques, Networked System, Software System\n", "\n", "\n", "\n", "Projects\n", "\n", "VLAN Building - Built a VLAN on Juniper switch and analyzed network congestion under UDP transmission\n", "\n", "Signal FIlter - Designed several different signal filters to realize bass booster for input music (using Matlab)\n", "\n", "\n", "\n", "------------------------------\n"]
  end

  def test_readFile
    assert_equal(@my_lines, readFile("resume.txt"))
  end

  def test_detectName
    name = detectName(['Zi Chen\n','\n'])
    assert_equal(['Zi Chen\n'],name)

    lines = [""]
    assert_raises(RuntimeError) {detectName(lines)}

    lines = ["zi chen"]
    assert_raises(RuntimeError) {detectName(lines)}
  end



  def test_detectEmail
    email = detectEmail(["zichen\n","zichen@seas.upenn.edu\n","\n"])
    assert_equal(["zichen@seas.upenn.edu\n"],email)

    email = detectEmail(["zichen\n","zichen@gmail.com\n","\n"])
    assert_equal(["zichen@gmail.com\n"],email)

    email = detectEmail(["\n","\n"])
    assert_equal([[]],email)

    email = detectEmail(["\n","zichen@seas.upenn.gov\n"])
    assert_equal([[]],email)

    email = detectEmail(["\n","zichen@.edu\n"])
    assert_equal([[]],email)
  end

  def test_detectCourses
    courses = detectCourses(["\n","Courses: CIT590, CIS505\n"])
    assert_equal(["CIT590, CIS505\n"],courses)

    courses = detectCourses(["\n","courses: CIT590, CIS505\n"])
    assert_equal(["\n"],courses)

    courses = detectCourses(["\n","Courses: -- -- CIT590, CIS505\n"])
    assert_equal(["CIT590, CIS505\n"],courses)

    courses = detectCourses(["\n","Courses: --  \n"])
    assert_equal(["\n"],courses)
  end

  def test_detectProjects
    projects = detectProjects(["\n","Projects","built a network\n","\n","did an experiment\n","\n","----------\n"])
    assert_equal(["built a network\n","did an experiment\n"],projects)

    projects = detectProjects(["\n","projects","built a network\n","\n","did an experiment\n","\n","----------\n"])
    assert_equal([],projects)

    projects = detectProjects(["\n","Projects","built a network\n","\n","\n","----------\n","did an experiment\n"])
    assert_equal(["built a network\n"],projects)
  end

  def test_detectEducation
    education = detectEducation(["Master of Science in CS - University of Pennsylvania\n","Bachelor of Science - Tsinghua University\n"])
    assert_equal(["Master of Science in CS - University of Pennsylvania\n","Bachelor of Science - Tsinghua University\n"],education)

    education = detectEducation(["Master of Science in CS - university of Pennsylvania\n","Bachelor of Science - Tsinghua university\n"])
    assert_equal(["Master of Science in CS - university of Pennsylvania\n","Bachelor of Science - Tsinghua university\n"],education)

    education = detectEducation(["Science in CS - University of Pennsylvania\n","Master of Science - MIT\n","projects in Stanford\n"])
    assert_equal([],education)
  end

  def test_surround_block        
    lines = surround_block("p", ["Hello\n", "World\n"])
    assert_equal(lines,["<p>\n", "Hello\n", "World\n", "</p>\n"])

    lines = surround_block("div", surround_block("p", ["Hello\n", "World\n"]))    
    assert_equal(lines,["<div>\n", "<p>\n", "Hello\n", "World\n", "</p>\n", "</div>\n"])

    lines = surround_block("li", surround_block("p", ["Hello\n", "World\n"])) 
    assert_equal(lines,["<li>\n", "<p>\n", "Hello\n", "World\n", "</p>\n", "</li>\n"])       

  end

  def test_modifyHTMl
    modifyHTMl(@file_name)

    lines = readFile(@file_name)

    assert_equal(lines[-1], "<div id=\"page-wrap\">")
    refute(lines[-2] == "</body>\n")
    refute(lines[-1] == "</html>\n")
  end

  def test_write_intro_section
    name = ["Arvind\n"]
    email = ["bhusnur@seas.upenn.edu\n"]

    write_intro_section(@file_name, name, email)

    lines = readFile(@file_name)

    assert(lines.include?(name[0]))
    assert(lines.include?("Email: " + email[0]))
  end

  def test_write_education_section
    write_education_section(@file_name, [])
    lines = readFile(@file_name)
    refute(lines.include?("Education\n"))

    education = ["Bachelors in Python\n", "Masters in Java\n"]
    write_education_section(@file_name, education)
    lines = readFile(@file_name)

    assert(lines.include?(education[0]))
    assert(lines.include?(education[1]))
  end

  def test_write_project_section
    write_project_section(@file_name, [])
    lines = readFile(@file_name)
    refute(lines.include?("Projects\n"))

    projects = ["Worked on the foreground background segmentation problem, which is a classic problem in computer vision.\n", "Compiled data on grade inflation. Published several papers on this topic in the journal of ivy league humor.\n"]
    write_project_section(@file_name, projects)
    lines = readFile(@file_name)
    assert(lines.include?(projects[0]))
    assert(lines.include?(projects[1]))
  end

  def test_write_course_section
    write_course_section(@file_name, [])
    lines = readFile(@file_name)
    refute(lines.include?("Courses\n"))

    courses = ["Algorithms, Forex training\n"]
    write_course_section(@file_name, courses)
    lines = readFile(@file_name)
    assert(lines.include?(courses[0]))
  end

  def test_write_end
    write_end(@file_name)
    lines = readFile(@file_name)
    assert_equal(lines[-3], "</div>\n")
    assert_equal(lines[-2], "</body>\n")
    assert_equal(lines[-1], "</html>\n")
  end


end